package com.example;

public class final2 {
    public static void main(String[] args) {
        String pidFinal = args[0];
        try {
            ProcessBuilder pb = new ProcessBuilder("java", "final3", pidFinal);
            pb.inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}