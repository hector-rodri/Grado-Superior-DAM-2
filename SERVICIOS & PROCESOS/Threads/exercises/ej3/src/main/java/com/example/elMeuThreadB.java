package com.example;

/**
 * Soluciona el  dead-lock.
 *
 * Reescriu el codi tal que elMeuThreadA i elMeuThreadB facin addicions
 * en paral-lel.
 *
 *
 */
public class elMeuThreadB implements Runnable {

    @Override
    public void run() {
        synchronized (Main.lockB) {
            for (int i = 0; i < Main.N; i++) {
                Main.valorB++;
            }
            synchronized (Main.lockA) {
                for (int i = 0; i < Main.N; i++) {
                    Main.valorA++;
                }
            }
        }
    }
}
