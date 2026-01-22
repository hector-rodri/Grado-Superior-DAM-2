package com.example;

import java.io.*;

public class pB {
    public static void main(String[] args) {
        long pid = ProcessHandle.current().pid();
        try {
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", ".", "com.example.p2", String.valueOf(pid));
            pb.inheritIO();
            Process child = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(child.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("From child: " + line);
            }
            child.waitFor();
        } catch (IOException | InterruptedException e) {
            System.err.println("The process was interrupted");
            e.printStackTrace();
        }

        System.out.println("The program ended");
    }
}