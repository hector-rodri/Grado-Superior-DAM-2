package com.example;

/**
 * Només has de modificar aquest fitxer per a la Pregunta 6
 */

import java.util.Queue;

public class Buffer {
    Queue<Integer> queue;
    int capacidad;

    public Buffer(int size) {
        queue = new LimitedQueue<>(size);
        capacidad = size;
    }

    public synchronized void put(int value) {
        while (queue.size() == capacidad) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.add(value);
        notifyAll();
    }

    public synchronized int get() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int a = -1;
        Integer result = queue.poll();
        if (result != null) {
            a = result;
        }
        notifyAll();
        return a;
    }
}