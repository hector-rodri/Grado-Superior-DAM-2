import java.io.*;

public class p3 {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String num = reader.readLine();
            int numParse = Integer.parseInt(num);

            long pid = ProcessHandle.current().pid();
            System.out.println("Soy el proceso 3 y mi pid es: " + pid);

            System.out.println("Valor recibido: " + numParse);
            numParse = numParse * numParse;
            System.out.println("Valor procesado: " + numParse);

        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
