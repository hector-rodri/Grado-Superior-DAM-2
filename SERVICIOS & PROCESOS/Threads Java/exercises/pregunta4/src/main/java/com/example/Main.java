package com.example;
/**
    Paral·lelitza el bucle de la multiplicació dels elements del vector v i explica el procediment.
    Tingues en compte que en el codi actual, la multiplicació és seqüencial.

    La tarea se puede dividir en distintos hilos y cada hilo multiplica un trozo del vector
    Cogemos nuestros 8 hilos y dividimos el vector en x trozos igual al numero de hilos es decir (N + hilos - 1) / hilos, creamos e inicializamos los hilos asignando un trozo de vector
    cada hilo se multiplica y se guarada, cada hilo espera que todos hayan acabado con join, al acabar el vector tendrá los resultaods

**/

public class Main {

    public static void main(String[] args)  throws InterruptedException{
        int N = 100000013;
        int[] v = new int[N];
        int hilos = 8;
        Thread[] threads = new Thread[hilos];
        int size = (N + hilos - 1) / hilos;

        for (int i = 0; i < N; i++) {
            v[i] = i;
        }

        // Aplicar paral·lelització utilitzant P threads
        for (int t = 0; t < hilos; t++) {
            int empieza = t * size;
            int acaba = 0;//????????????????????????????

            threads[t] = new Thread(() -> {
                //No me llego a acordar/entender prefiero dejartelo sin error y que almenos compile
            });

            threads[t].start();
        }
        for (int t = 0; t < hilos; t++) {
            threads[t].join();
        }
        for (int i = 0; i < N; i++) {
            if (v[i] != i * 6) {
                System.out.println("Resposta incorrecta");
                System.exit(1);
            }
        }
        System.out.println("Correcte");
    }

}
