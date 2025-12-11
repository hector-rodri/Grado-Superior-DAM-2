import java.io.*;
import java.util.*;

public class p1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe un número entero");
        int num = sc.nextInt();
        String num2 = String.valueOf(num);

        try {
            ProcessBuilder pb = new ProcessBuilder("java", "p2");
            pb.redirectErrorStream(true);
            Process p2 = pb.start();

            OutputStreamWriter writer = new OutputStreamWriter(p2.getOutputStream());
            writer.write(num2);
            writer.flush();
            writer.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p2.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println("Output 2: " + line);
            }
            
        } catch (IOException io) {
            io.printStackTrace();
        }
        sc.close();
    }
}
