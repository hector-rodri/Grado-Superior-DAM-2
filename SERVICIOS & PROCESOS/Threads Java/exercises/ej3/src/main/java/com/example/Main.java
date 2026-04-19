package com.example;

/**
 * *  Soluciona els errors.
 */
public class Main {
    static final int N = 1000000; // Try a smaller value for N
    static final Object lockA = new Object();
    static final Object lockB = new Object();
    static int valorA = 0;
    static int valorB = 0;

    public static void main(String[] args) {
        Thread[] threads = new Thread[2];

        threads[0] = new Thread(new Thread());
        threads[1] = new Thread(new Thread());
        for (int i = 0; i < 2; i++) {
            threads[i].start();
        }
        for (int i = 0; i < 2; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("I finished");
    }
}
