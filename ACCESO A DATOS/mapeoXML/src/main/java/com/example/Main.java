package com.example;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class Main {
    public static void main(String[] args) {
        File file = new File("players.xml");
        abrir_XML_DOM(file);
    }

    public static int abrir_XML_DOM(File fichero) {
        Document doc = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();

            doc = builder.parse(fichero);
            String salida = recorrerDOMyMostrar(doc);
            System.out.println(salida);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String[] procesarLibro(Node nodo) {
        String[] datos = new String[20];
        Node temp = null;
        int contador = 0;

        // No hay atributos en <Player>, así que no usamos getAttributes()

        NodeList nodos = nodo.getChildNodes(); // hijos de <Player>
        for (int i = 0; i < nodos.getLength(); i++) {
            temp = nodos.item(i);
            if (temp.getNodeType() == Node.ELEMENT_NODE) {
                datos[contador] = temp.getTextContent(); // guarda el texto de <Name>, <Position>, etc.
                contador++;
            }
        }
        return datos;
    }

    public static String recorrerDOMyMostrar(Document doc) {
        String[] datos_nodo;
        String salida = "";

        Node raiz = doc.getDocumentElement(); // <FootballPlayers>
        NodeList nodos = raiz.getChildNodes(); // lista de <Player>

        for (int i = 0; i < nodos.getLength(); i++) {
            Node node = nodos.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                datos_nodo = procesarLibro(node); // procesa cada <Player>
                salida += "\nNombre: " + datos_nodo[0];
                salida += "\nPosición: " + datos_nodo[1];
                salida += "\nNacionalidad: " + datos_nodo[2];
                salida += "\nClub: " + datos_nodo[3];
                salida += "\n-------------------------";
            }
        }
        return salida;
    }
}