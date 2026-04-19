package bug2;

/**
 * Why does this code not block? We took the same lock twice!
 * Because both synchronized blocks are synchronized on the same object (this),
 * so the thread that holds the lock can enter both blocks without blocking itself.
 * There is nothing to modify for this example. Just explain why we do NOT
 * have a deadlock.
 */
public class MyThread implements Runnable {
    static int i;

    @Override
    public void run() {
        synchronized (this) {
            synchronized (this) {
                i++;
            }
        }
    }
}
