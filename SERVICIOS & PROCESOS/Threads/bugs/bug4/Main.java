package bug4;

/**
 * Why doesn't this program end? (Hint: volatile)
 * The JVM may optimize the code by keeping the value of keepRunning in a register,
 * so the thread may never see the updated value when another thread modifies it.
 * Fix the problem changing a single line of code.
 */
public class Main extends Thread {
    volatile boolean keepRunning = true;//The change is here, we put volatile to avoid optimization issues

    public void run() {
        long count = 0;
        while (keepRunning) {
            count++;
        }

        System.out.println("Thread terminated." + count);
    }

    public static void main(String[] args) throws InterruptedException {
        Main t = new Main();
        t.start();
        Thread.sleep(1000);
        t.keepRunning = false;
        System.out.println("keepRunning set to false.");
        t.join();
        System.out.println("The thread has ended.");
    }
}
