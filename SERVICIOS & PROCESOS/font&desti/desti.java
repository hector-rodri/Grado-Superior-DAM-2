import sun.misc.*;

public class desti {
    public static void main(String[] args) {
        long pid = ProcessHandle.current().pid();
        System.out.println("PID_DESTI=" + pid);

        try {
            Signal.handle(new Signal("TERM"), new SignalHandler() {
                @Override
                public void handle(Signal signal) {
                    System.out.println("Recibido SIGTERM finalizando desti");
                    System.exit(0);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
