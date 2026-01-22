package com.example;

public class final3 {
    public static void main(String[] args) {

        String pidFinal = args[0];
        System.out.println("Process final3 started. Sending SIGINT to PID " + pidFinal);

        try {
            ProcessBuilder pb = new ProcessBuilder("kill", "-SIGINT", pidFinal);
            pb.inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}