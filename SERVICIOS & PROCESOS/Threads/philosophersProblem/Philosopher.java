package philosophersProblem;

public class Philosopher implements Runnable {
    private final Object leftFork;
    private final Object rightFork;
    private final int id;

    public Philosopher(int id, Object leftFork, Object rightFork) {//Cada filósofo tiene un ID y dos tenedores a su izquierda y derecha
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.id = id;
    }

    private void sleep() {//El tiempo que tarda 
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {//Evita el deadlock al coger los tenedores en orden diferente según el ID del filósofo
        if (id % 2 == 0) {//Filósofos pares cogen el tenedor izquierdo primero
            synchronized (leftFork) {
                sleep();
                synchronized (rightFork) {
                    System.out.println("Philosopher " + id + " is eating");
                }
            }
        } else {
            synchronized (rightFork) {//Filósofos impares cogen el tenedor derecho primero
                sleep();
                synchronized (leftFork) {
                    System.out.println("Philosopher " + id + " is eating");
                }
            }
        }
    }

}
