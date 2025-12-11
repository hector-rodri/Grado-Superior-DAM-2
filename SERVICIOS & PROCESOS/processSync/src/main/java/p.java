import java.io.*;
import java.util.*;
import sun.misc.*;

public class p {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long pid = ProcessHandle.current().pid();

        System.out.println("PID del procés pare: " + pid);
        System.out.print("Introdueix una senyal (ALRM, TERM, INT, HUP, KILL): ");
        String senyal = sc.nextLine().toUpperCase();

        Signal.handle(new Signal("ALRM"), sig -> {
            System.out.println("He rebut SIGALRM → faig una pausa breu...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Fi");
            System.exit(0);
        });

        Signal.handle(new Signal("TERM"), sig -> {
            System.out.println("He rebut SIGTERM → tancant el procés p");
            System.exit(0);
        });

        Signal.handle(new Signal("INT"), sig -> {
            System.out.println("He rebut SIGINT → interrupció detectada");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(0);
        });

        Signal.handle(new Signal("HUP"), sig -> {
            System.out.println("He rebut SIGHUP → ignorant la senyal");
        });

        try {
            ProcessBuilder pb = new ProcessBuilder("java", "p2", senyal, String.valueOf(pid));
            pb.redirectErrorStream(true);
            Process p2 = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p2.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Sortida de p2: " + line);
            }
            p2.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}