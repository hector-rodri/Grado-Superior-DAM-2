package com.example;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class Main {
    public static void main(String[] args) {
        File file = new File("players.xml");
        open_XML_DOM(file);
    }

    public static int open_XML_DOM(File file) {
        Document doc = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();

            doc = builder.parse(file);
            String exit = processDOM(doc);
            System.out.println(exit);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String[] processPlayer(Node nodo) {
        String[] data = new String[20];
        Node temp = null;
        int count = 0;

        NodeList nodos = nodo.getChildNodes(); 
        for (int i = 0; i < nodos.getLength(); i++) {
            temp = nodos.item(i);
            if (temp.getNodeType() == Node.ELEMENT_NODE) {
                data[count] = temp.getTextContent(); 
                count++;
            }
        }
        return data;
    }

    public static String processDOM(Document doc) {
        String[] node_data;
        String exit = "";

        Node raiz = doc.getDocumentElement(); 
        NodeList nodos = raiz.getChildNodes();

        for (int i = 0; i < nodos.getLength(); i++) {
            Node node = nodos.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                node_data = processPlayer(node);
                exit += "\nName: " + node_data[0];
                exit += "\nPosition: " + node_data[1];
                exit += "\nNationality: " + node_data[2];
                exit += "\nClub: " + node_data[3];
                exit += "\n-------------------------";
            }
        }
        return exit;
    }
}