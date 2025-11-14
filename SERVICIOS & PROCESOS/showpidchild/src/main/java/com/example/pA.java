package com.example;

import java.io.IOException;

public class pA {
    public static void main(String[] args) {
        long pid = ProcessHandle.current().pid();
        try {
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", ".", "com.example.p2", String.valueOf(pid));
            pb.inheritIO();
            Process child = pb.start();
            child.waitFor();
        } catch (IOException | InterruptedException e) {
            System.err.println("The process was interrupted");
            e.printStackTrace();
        }

        System.out.println("The program ended");
    }
}