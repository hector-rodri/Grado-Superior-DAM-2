package com.example;

/**
 * Paral·lelitza el bucle de la multiplicació dels elements del vector v i
 * explica el procediment.
 * Tingues en compte que en el codi actual, la multiplicació és seqüencial.
 **/

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int N = 100000013;
        int[] v = new int[N];
        int P = 6;
        Thread[] Threads = new Thread[P];
        int size = (N + P - 1) / P;
        int elements = 13;

        for (int i = 0; i < N; i++) {
            v[i] = i;
        }

        // Aplicar paral·lelització utilitzant P threads
        for (int t = 0; t < P; t++) {
            final int inicio = t * size;
            final int fin = Math.min(inicio + size, N);

            Threads[t] = new Thread(() -> {
                for (int i = inicio; i < fin; i++) {
                    v[i] = v[i] * elements;
                }
            });

            Threads[t].start();
        }

        for (int t = 0; t < P; t++) {
            Threads[t].join();
        }
        for (int i = 0; i < N; i++) {
            if (v[i] != i * elements) {
                System.out.println("Resposta incorrecta");
                System.exit(1);
            }
        }
        System.out.println("Correcte");

    }
}
