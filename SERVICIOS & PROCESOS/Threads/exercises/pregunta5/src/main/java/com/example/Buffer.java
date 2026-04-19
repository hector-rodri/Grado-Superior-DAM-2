package com.example;
/**
    Només has de modificar aquest fitxer per a la Pregunta 5.
**/
public class Buffer {
    private int item;
    private boolean espacio = false;

    synchronized void produir(int valor) {
        while (espacio) {
            try {
                wait();
            } catch (InterruptedException excep) {
                excep.printStackTrace();
            }
        }
        item = valor;
        espacio = true;
        notify();
    }

    synchronized int consumir() {
        while (!espacio) {
            try {
                wait();
            } catch (InterruptedException excep) {
                excep.printStackTrace();
            }
        }
        espacio = false;
        notify();
        return item;
    }
}