package com.example;

public class Main {
    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Cores: " + cores);

        for (int i = 0; i < cores; i++) {
            int threadId = i;
            Thread t = new Thread(() -> {
                System.out.println("Hello from thread #" + threadId);
            });
            t.start();
        }
    }
}