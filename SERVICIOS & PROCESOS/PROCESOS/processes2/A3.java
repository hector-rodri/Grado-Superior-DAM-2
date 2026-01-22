import java.io.*;

public class A3 {
    public static void main(String[] args) {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String numString = reader.readLine();
            int numInt = Integer.parseInt(numString);

            long pid = ProcessHandle.current().pid();
            System.out.println("Soy A3 y mi pid es: " + pid);
            System.out.println("Me ha llegado este numero: " + numInt);
            numInt = numInt * 3;
            System.out.println("Este es el resultado final: " + numInt);

        } catch (IOException e) {
            System.err.println("Error");
            e.printStackTrace();
        }

    }
}
