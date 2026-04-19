package com.example;
/**
 * 1. Explica per què aquest programa mai termina (es queda penjat)?
 * 2. Soluciona aquest problema canviant només una línia de codi i senyala el canvi amb un comentari.
 * 
 * La variable "continua" necesita actualizarse al momento de usarla y que los otros hilos puedan ver su valor modificado al momento, 
 * si no tenemos la propiedad volatile en nuestra variable implica que no se va actualizar su valor al momento.
 * Actualizamos la variable como si fuera nuestra memoria ram ,es decir, al instante para que los otros hilos puedan ver actualizado su valor
 */
public class Main extends Thread {
    volatile boolean continua = true;//Aquí esta el cambio

    public void run() {
        long count = 0;
        while (continua) {
            count++;
        }

        System.out.println("El thread ha terminat" + count);
    }

    public static void main(String[] args) throws InterruptedException {
        Main t = new Main();
        t.start();
        Thread.sleep(1000);
        t.continua = false;
        System.out.println("continua posat a false.");
        t.join();
        System.out.println("El thread ha terminat.");
    }
}
