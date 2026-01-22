import java.io.*;

public class font {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("java", "desti");
            pb.redirectErrorStream(true);
            Process desti = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(desti.getInputStream()));
            String linePid = reader.readLine();
            long pidDesti = 0;
            if (linePid != null && linePid.startsWith("PID_DESTI=")) {
                pidDesti = Long.parseLong(linePid.split("=")[1]);
            }
            System.out.println(linePid);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            new ProcessBuilder("kill", "-TERM", String.valueOf(pidDesti)).start();

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Salida desti: " + line);
            }

            try {
                desti.waitFor();
            } catch (InterruptedException e) {
                System.err.println("El procés fill ha estat interromput");
            }
            System.out.println("Font confirma que desti ha acabado");

        } catch (IOException e) {
            System.err.println("Error d'E/S en executar el procés fill");
        }
    }
}