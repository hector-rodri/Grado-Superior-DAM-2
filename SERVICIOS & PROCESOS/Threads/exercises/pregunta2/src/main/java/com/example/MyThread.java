package com.example;
/**
 * 1. Explica en què consisteix el concepte de condició de carrera en programació multifil.
 * 2. Per què no hi ha condició de carrera en aquest codi?
 * 3. Modifica només una línia de codi per que hi hagi condició de carrera i senyala el canvi amb un comentari.
 * 
 * 
 * 
 * El problema es que se intenta ejecutar algo que es lo mismo ya que a = b pero al crear un nuevo string consigues que la variable no tenga el mismo "valor"
 * Los hilos esperan a que los otros hayan acabado para entrar a actuar en la misma memoria
 */
public class MyThread implements Runnable {
    static final String a = "BLOQUEIG";
    static final String b = new String("BLOQUEIG");//El cambio está aquí
    int id;
    static int valor = 0;

    MyThread(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        if (id == 0) {
            synchronized (a) {
                for (int i = 0; i < Main.N; i++)
                    valor = valor + 3;
            }
        } else {
            synchronized (b) {
                for (int i = 0; i < Main.N; i++)
                    valor = valor + 3;
            }
        }
    }
}
