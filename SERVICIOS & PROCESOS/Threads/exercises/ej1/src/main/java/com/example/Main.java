package com.example;

public class Main {
    public static void main(String[] args) {
        int cores = 18;

        for (int i = 0; i < cores; i++) {
            int threadId = i;
            Thread t = new Thread(() -> {
                System.out.println("Fil " + threadId + " iniciat");
            });
            t.start();
        }

        System.out.println();
    }
}