package com.example;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("ls", "-l");
            Process process = pb.start();
            InputStreamReader inputReader = new InputStreamReader(process.getInputStream());
            BufferedReader reader = new BufferedReader(inputReader);
            String line;
            
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
