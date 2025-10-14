package com.example;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class Main {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();

            SAXParser parser = factory.newSAXParser();

            Libro handler = new Libro();
            parser.parse("libros.xml", handler);

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}

// public int abrir_XML_SAX(ManejadorSAX sh, SAXParser parser ){
// try{
// SAXParserFactory factory=SAXParserFactory.newInstance();
// //Se crea un objeto SAXParser para interpretar el documento
// //XML.
// parser=factory.newSAXParser();
// //Se crea una instancia del manejador que será el que recorra
// //el documento //XML secuencialmente
// sh=new ManejadorSAX();
// return 0;
// } catch (Exception e){ e.printStackTrace(); return -1; }
// }