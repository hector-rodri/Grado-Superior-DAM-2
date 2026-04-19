package com.example;

/**
 * Només has de modificar aquest fitxer per a la Pregunta 6
 */
import java.util.Queue;

public class Buffer {
    Queue<Integer> queue;
    int quantity;

    public Buffer(int size) {
        queue = new LimitedQueue<>(size);
        quantity = size;
    }

    synchronized void put(int value) {
        while (queue.size() == quantity) {
            try {
                wait();
            } catch (InterruptedException excep) {
                excep.printStackTrace();
            }
        }

        queue.add(value);
        notifyAll();
    }

    synchronized int get() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException excep) {
                excep.printStackTrace();
                System.out.println("Interrupmido");
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