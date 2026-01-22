package com.example;

import java.util.Scanner;
import java.io.*;

public class pC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Write something: ");
        String input = sc.nextLine();

        try {
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", ".", "com.example.pD");
            pb.redirectErrorStream(true);
            Process process = pb.start();

            OutputStreamWriter writer = new OutputStreamWriter(process.getOutputStream());
            writer.write(input);
            writer.flush();
            writer.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Output: " + line);
            }

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        sc.close();
    }
}