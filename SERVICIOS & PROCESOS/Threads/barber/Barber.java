package barber;

public class Barber extends Thread {
    @Override
    public void run() {
        int servedClients = 0;

        while (servedClients < Main.clientsCount) {
            try {
                Main.clients.acquire();//Espera a que un cliente esté listo
                Main.chairs.release(); //Libera una silla para el cliente
                Main.barberReady.release(); //Indica que el barbero está listo para atender al cliente
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(100); //Simula el tiempo que tarda el barbero en atender al cliente
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Barber served client");
            servedClients++;//Incrementa el contador de clientes atendidos
        }
    }
}
