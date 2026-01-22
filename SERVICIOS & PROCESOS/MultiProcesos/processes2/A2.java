import java.io.*;

public class A2 {
    public static void main(String[] args) {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String numString = reader.readLine();
            int numInt = Integer.parseInt(numString);

            long pid = ProcessHandle.current().pid();
            System.out.println("Soy A2 y mi pid es: " + pid);
            System.out.println("Me ha llegado este numero: " + numInt);
            numInt = numInt + 10;
            System.out.println("Este es el resultado: " + numInt);

            ProcessBuilder pb = new ProcessBuilder("java", "A3");
            pb.redirectErrorStream(true);
            Process A3 = pb.start();

            OutputStreamWriter writer = new OutputStreamWriter(A3.getOutputStream());
            writer.write(numInt + "\n");
            writer.flush();

            try (BufferedReader readerProcess = new BufferedReader(new InputStreamReader(A3.getInputStream()))) {
                String line;
                while ((line = readerProcess.readLine()) != null) {
                    System.out.println("Salida de A3: " + line);
                }
            }

        } catch (IOException e) {
            System.err.println("Error d'E/S en executar el procés fill");
        }
    }
}
