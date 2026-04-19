package com.example;

/**
 * Explica aquest patró de disseny.
 * Què podria anar malament? Quantes instàncies es creen?
 * En un entorno multithread, se podria crear una condición de carrera ya que dos ejecuciones entran al mismo tiempo en getInstance, corrigiendolo se crea una instancia
 *
 * Modifica una línia de codi i senyala el canvi.
 */

public class Singleton {
    private static Singleton instance;
    private static int numberOfInstances = 0;

    private Singleton() {
        
    }

    public static synchronized Singleton getInstance() {//CANVI
        if (instance == null) {
            System.out.println("Creating only one instance");
            instance = new Singleton();
            ++numberOfInstances;
        }
        return instance;
    }

    public static int getNumberOfInstances() {
        return numberOfInstances;
    }
}
