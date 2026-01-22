package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class pD {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                System.out.println(line.toUpperCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}