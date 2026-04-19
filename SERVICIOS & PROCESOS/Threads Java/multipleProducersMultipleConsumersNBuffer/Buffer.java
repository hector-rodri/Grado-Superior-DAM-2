package multipleProducersMultipleConsumersNBuffer;

import java.util.*;

public class Buffer {
    private final Queue<Integer> bufferQueue;
    private final int maxCapacity;

    public Buffer(int size) {
        this.bufferQueue = new LinkedList<>();
        this.maxCapacity = size;
    }

    public synchronized void put(int item) {
        while (bufferQueue.size() == maxCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        bufferQueue.add(item);
        notifyAll();
    }

    public synchronized int get() {
        while (bufferQueue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int item = bufferQueue.poll();
        notifyAll();
        return item;
    }
}
