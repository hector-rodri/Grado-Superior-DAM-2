package com.example;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class padre {
    public static void main(String[] args) throws Exception {
        System.out.println("Padre PID: " + ProcessHandle.current().pid());
        ProcessBuilder pb = new ProcessBuilder("java", "fill");
        pb.redirectErrorStream(true);
        Process child = pb.start();

        Thread reader = new Thread(() -> {
            try {
                InputStreamReader inputReader = new InputStreamReader(child.getInputStream());
                BufferedReader bufferReader = new BufferedReader(inputReader);
                String line;
                while ((line = bufferReader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        });
        reader.start();

        boolean end = child.waitFor(15, TimeUnit.SECONDS);
        if (end) {
            System.out.println("El hijo ha acabado: " + child.exitValue());
        } else {
            System.out.println("El hijo no ha acabado, se le enviará kill");
            child.destroyForcibly();
            child.waitFor();
            System.out.println("El hijo ha sido destruido:" + child.exitValue());
        }
        reader.join();
        System.out.println("Padre finalizado.");
    }
}
