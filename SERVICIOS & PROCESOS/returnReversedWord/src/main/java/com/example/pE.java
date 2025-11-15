package com.example;

import java.io.*;

public class pE {
    public static void main(String[] args) throws IOException {
        try {
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", ".", "com.example.pF");
            pb.redirectErrorStream(true);
            Process process = pb.start();
            OutputStreamWriter writer = new OutputStreamWriter(process.getOutputStream());
            writer.write("Hector");
            writer.flush();
            writer.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Reversed word: " + line);
            }
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
