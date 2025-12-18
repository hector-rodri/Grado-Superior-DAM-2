package com.sax;

import java.io.*;//Import libraries
import javax.xml.parsers.*;
import org.xml.sax.*;

public class Main {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();//Create a new SAXParserFactory instance
            SAXParser parser = factory.newSAXParser();//Use the factory to create a new SAXParser
            Player handler = new Player();//Create an instance of the our handler
            parser.parse("recursos/players.xml", handler);//Parse the XML file using the handler

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}