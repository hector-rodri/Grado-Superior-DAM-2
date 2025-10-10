package com.example;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            Process process = Runtime.getRuntime().exec("ls -l");
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
