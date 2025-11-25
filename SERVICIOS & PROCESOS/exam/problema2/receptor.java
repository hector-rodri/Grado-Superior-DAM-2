import sun.misc.SignalHandler;
import sun.misc.Signal;

public class receptor {
    public static void main(String[] args) {
        long pid = ProcessHandle.current().pid();
        String pid2 = String.valueOf(pid);
        System.out.println(pid2);

        Signal.handle(new Signal("USR1"), new SignalHandler() {
            @Override
            public void handle(Signal sig) {
                System.out.println("He recibido SIGUSR1, finalizando receptor");
                System.exit(0);
            }
        });

    } 
}