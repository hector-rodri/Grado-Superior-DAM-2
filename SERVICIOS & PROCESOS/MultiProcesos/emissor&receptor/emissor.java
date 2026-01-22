import java.io.*;
import java.util.concurrent.TimeUnit;

public class emissor {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        long pid = ProcessHandle.current().pid();
        System.out.println("Soy el emissor y mi pid es: " + pid);
        try {
            ProcessBuilder pb = new ProcessBuilder("java", "receptor");
            pb.redirectErrorStream(true);
            Process p2 = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p2.getInputStream()));
            String pidHijo;
            while ((pidHijo = reader.readLine()) != null) {
                System.out.println("Pid hijo: " + pidHijo);
            }

            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            ProcessBuilder p1 = new ProcessBuilder("kill", "-USR1 ", (pidHijo));
            p1.start();

            BufferedReader reader2 = new BufferedReader(new InputStreamReader(p2.getInputStream()));
            String line;
            while ((line = reader2.readLine()) != null) {
                System.out.println("Output: " + line);
            }

            boolean finished = p2.waitFor(2, TimeUnit.SECONDS);
            if (finished) {
                System.out.println("El emisor confirma que el recpetor ha acabado");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("Interrumpido");
            e.printStackTrace();
        }

    }
}
