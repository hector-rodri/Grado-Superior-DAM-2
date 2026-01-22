package com.example;

import java.io.*;

public class pG {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine();
            String reversed = new StringBuilder(input).reverse().toString();
            System.out.println(reversed);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
