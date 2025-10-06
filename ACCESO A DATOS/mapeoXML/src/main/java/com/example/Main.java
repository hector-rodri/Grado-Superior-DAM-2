package com.example;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class Main {
    public static void main(String[] args) {
        File file = new File("mapeoXML\\players.xml");
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
            
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    protected String[] procesarLibro(Node nodo) {
        String datos[] = new String[4];
        Node temp = null;
        int contador = 0;
        // Obtiene el primer atributo del nodo (en este caso el atributo id)
        NamedNodeMap atributos = nodo.getAttributes();
        datos[0] = atributos.item(0).getNodeValue();
        // Obtiene la lista de nodos hijos del nodo libro
        NodeList nodos = nodo.getChildNodes();
        for (int i = 0; i < nodos.getLength(); i++) {
            temp = nodos.item(i);
            if (temp.getNodeType() == Node.ELEMENT_NODE) {
                // IMPORTANTE: para obtener el texto con el título y autor se accede al nodo
                // hijo temp y se
                // usa su valor.
                datos[contador] = temp.getFirstChild().getNodeValue();
                contador++;
            }
        }
        return datos;
    }

    public String recorrerDOMyMostrar(Document doc) {
        String datos_nodo[] = null;
        String salida = "";
        // Obtiene el primer nodo del DOM (primer hijo)
        Node raiz = doc.getFirstChild();
        // Obtiene una lista de todos los nodos hijos del nodo raíz.
        NodeList nodos = raiz.getChildNodes();
        // Procesa los nodos hijos
        for (int i = 0; i < nodos.getLength(); i++) {
            Node node = nodos.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                // Es un nodo libro
                datos_nodo = procesarLibro(node);
                salida += "\n Titulo: " + datos_nodo[0];
                salida += "\n Autor: " + datos_nodo[1];
                salida += "\n Año: " + datos_nodo[2];
            }
        }
        return salida;
    }
}

