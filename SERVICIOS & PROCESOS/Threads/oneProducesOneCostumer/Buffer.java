package oneProducerOneConsumer;

public class Buffer {
    private int a;
    private boolean isFull = false;

    public synchronized void put(int value) {
        while (isFull) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        a = value;
        isFull = true;
        notify();
    }

    public synchronized int get() {
        while (!isFull) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        isFull = false;
        notify();
        return a;
    }
}
