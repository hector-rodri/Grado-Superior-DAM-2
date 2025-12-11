import java.io.*;

public class p2 {
    public static void main(String[] args) throws Exception {
        long pid = ProcessHandle.current().pid();
        String senyal = args[0];
        String pidPare = args[1];

        System.out.println("Procés p2 iniciat");
        System.out.println("PID:" + pid);
        System.out.println("PID del pare: " + pidPare);
        System.out.println("Senyal rebuda: " + senyal);

        try {
            ProcessBuilder pb = new ProcessBuilder("java", "p3", senyal, pidPare);
            pb.redirectErrorStream(true);
            Process p3 = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p3.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Sortida p3: " + line);
            }

            if (p3.waitFor() == 0) {
                Process p = new ProcessBuilder("kill", "-HUP", pidPare).start();
                int exitCode = p.waitFor();
                if (exitCode == 0) {
                    System.out.println("SIGHUP enviat al procés p");
                } else {
                    System.err.println("Error en enviar SIGHUP");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
