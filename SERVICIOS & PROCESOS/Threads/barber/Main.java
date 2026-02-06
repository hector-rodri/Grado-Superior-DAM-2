package barber;

import java.util.concurrent.Semaphore;

public class Main {
    public final static int TOTAL_CHAIRS = 3;
    public final static int TOTAL_CLIENTS = 7;
    public final static int SERVED_CLIENT = 1;
    public final static int UNSERVED_CLIENT = 2;
    public static int[] leftClients = new int[TOTAL_CLIENTS];
    public static Semaphore clients = new Semaphore(0);  
    public static Semaphore barberReady = new Semaphore(0);   
    public static Semaphore chairs = new Semaphore(TOTAL_CHAIRS); 
    public static int clientsCount = TOTAL_CLIENTS;

    public static void main(String[] args) throws InterruptedException {
        Thread barberThread = new Barber();
        Thread[] clientThreads = new Client[clientsCount];

        for (int i = 0; i < clientsCount; i++) {
            clientThreads[i] = new Client(i);
        }

        barberThread.start();
        for (Thread clientThread : clientThreads) {
            clientThread.start();
            Thread.sleep(100);
        }

        barberThread.join();
        for (var thread: clientThreads) {
            thread.join();
        }

        int unservedClients = 0;
        for (var client: leftClients) {
            if (client == UNSERVED_CLIENT) {
                unservedClients++;
            }
        }
        System.out.println("There were " + unservedClients + " unserved clients");
    }
}
