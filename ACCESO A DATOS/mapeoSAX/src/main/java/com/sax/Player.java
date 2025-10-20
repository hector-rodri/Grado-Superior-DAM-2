package com.sax;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class Player extends DefaultHandler {

    private StringBuilder value;

    public Player() {
        this.value = new StringBuilder();
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        this.value.setLength(0);
        this.value.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "Player":
                System.out.println("");
                break;
            case "Name":
                System.out.println("Name: " + this.value.toString());
                break;
            case "Position":
                System.out.println("Position: " + this.value.toString());
                break;
            case "Nationality":
                System.out.println("Nationality: " + this.value.toString());
                break;
            case "Club":
                System.out.println("Club: " + this.value.toString());
                break;
        }
    }
}
