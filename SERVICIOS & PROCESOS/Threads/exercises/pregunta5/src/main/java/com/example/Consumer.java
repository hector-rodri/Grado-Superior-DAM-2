package com.example;
/**
 * No modifiquis aquest codi per a l'examen
 */
public class Consumer implements Runnable {
    private final Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < Main.N; i++) {
            int valor = buffer.consumir();
            if (valor != i) {
                System.out.println("Valor incorrecte. Es suposa que hauria d'obtenir " + i + " però he rebut " + valor);
                System.exit(1);
            }
        }
        System.out.println("He terminat correctament");
    }

}
