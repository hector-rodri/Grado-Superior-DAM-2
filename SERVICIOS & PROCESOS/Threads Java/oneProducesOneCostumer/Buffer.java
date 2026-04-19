package oneProducerOneConsumer;

public class Buffer {
    private int a;
    private boolean isFull = false;

    public synchronized void put(int value) {
        while (isFull) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
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
                e.printStackTrace();
            }
        }
        isFull = false;
        notify();
        return a;
    }
}
