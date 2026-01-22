import java.io.*;
import java.util.*;

public class p2 {
    public static void main(String[] args) {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String num = reader.readLine();
            int numParse = Integer.parseInt(num);

            long pid = ProcessHandle.current().pid();
            System.out.println("Soy el proceso 2 y mi pid es: " + pid);

            System.out.println("Valor recibido: " + numParse);
            numParse = numParse * 2;
            System.out.println("Valor procesado: " + numParse);

            ProcessBuilder pb = new ProcessBuilder("java", "p3");
            pb.redirectErrorStream(true);
            Process p3 = pb.start();

            String num2 = String.valueOf(numParse);

            OutputStreamWriter writer = new OutputStreamWriter(p3.getOutputStream());
            writer.write(num2);
            writer.flush();
            writer.close();

            BufferedReader reader2 = new BufferedReader(new InputStreamReader(p3.getInputStream()));
            String line;
            while ((line = reader2.readLine()) != null) {
                System.out.println("Output 3: " + line);
            }

        } catch(IOException io) {
            io.printStackTrace();
        } 
    }
}
