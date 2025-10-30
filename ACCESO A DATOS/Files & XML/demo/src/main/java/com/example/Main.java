package com.example;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Main m = new Main();
        m.EscribeFicheroTexto();
    }

    public void EscribeFicheroTexto() {
        // Crea el String con la cadena XML
        String texto = "<Libros><Libro><Titulo>El Capote</Titulo></Libro></Libros>";

        // Guarda en nombre el nombre del archivo que se creará
        String nombre = "libros.xml";

        try {
            // Se crea un nuevo objeto FileWriter
            FileWriter fichero = new FileWriter(nombre);

            // Se escribe el fichero
            fichero.write(texto + "\n");
            fichero.close();
        } catch (IOException ex) {
            System.out.println("error al acceder al fichero");
        }
    }
}