package barber;

public class Client extends Thread {
    private final int id;

    public Client(int id) {
        super();
        this.id = id;
    }

    @Override
    public void run() {
        try {
            if (Main.chairs.tryAcquire()) {//Intenta sentarse, si hay sillas disponibles
                System.out.println("Client " + id + " is waiting for haircut");
                System.out.println("Available seats: " + Main.chairs.availablePermits());
                Main.clients.release();//Indica que hay un cliente esperando
                Main.barberReady.acquire();//Espera a que el barbero esté listo para cortar
                System.out.println("Client " + id + " is served by the barber");
                Main.leftClients[id] = Main.SERVED_CLIENT;//Marca al cliente como atendido
                Main.chairs.release();//Libera la silla después de ser atendido
            } else {
                System.out.println("Client " + id + " left unserved");
                Main.leftClients[id] = Main.UNSERVED_CLIENT;//Marca al cliente como no atendido
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
