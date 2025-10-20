package com.sax;

import java.io.*;
import javax.xml.parsers.*;
import org.xml.sax.*;

public class Main {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();

            SAXParser parser = factory.newSAXParser();

            Player handler = new Player();
            parser.parse("recursos/players.xml", handler);

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}