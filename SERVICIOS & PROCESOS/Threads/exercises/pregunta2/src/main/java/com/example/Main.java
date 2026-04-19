package com.example;
/**
 * Aquest codi no s'ha de modificar per a l'examen
 */
public class Main {
    static final int N = 100000;
    static int N_ITERATIONS = 100;

    public static void main(String[] args) {
        Thread[] threads = new Thread[2];
        boolean sw = true;
        for (int j = 0; j < N_ITERATIONS; j++) {
            MyThread.valor = 0;
            for (int i = 0; i < 2; i++) {
                threads[i] = new Thread(new MyThread(i));
                threads[i].start();
            }
            for (int i = 0; i < 2; i++) {
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (MyThread.valor != 2 * 3 * N) {
                System.out.println("i era different de " + 2 * 3 * N + ", de fet és " + MyThread.valor);
                sw = false;
            }
        }
        if (sw) {
            System.out.println("Algo raro està tenint lloc");
        }
    }
}
