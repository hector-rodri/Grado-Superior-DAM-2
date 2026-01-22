import java.io.*;
import java.util.*;

public class A1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce un número entero");
        int num = sc.nextInt();

        try {
            ProcessBuilder pb = new ProcessBuilder("java", "A2");
            pb.redirectErrorStream(true);
            Process A2 = pb.start();

            OutputStreamWriter writer = new OutputStreamWriter(A2.getOutputStream());
            writer.write(num + "\n");
            writer.flush();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(A2.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("Salida de A2: " + line);
                }
            }

        } catch (IOException e) {
            System.err.println("Error d'E/S en executar el procés fill");
        }
        sc.close();
    }
}
