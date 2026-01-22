package com.example;

import java.io.*;

public class pF {
    public static void main(String[] args) throws Exception {
        try {
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", ".", "com.example.pG");
            pb.redirectErrorStream(true);
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            OutputStreamWriter writer = new OutputStreamWriter(process.getOutputStream());
            writer.write(reader.readLine());
            writer.flush();
            writer.close();
            BufferedReader processReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = processReader.readLine()) != null) {
                System.out.println(line);
            }
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
