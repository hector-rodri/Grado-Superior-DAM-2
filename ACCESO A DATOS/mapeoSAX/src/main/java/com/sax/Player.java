package com.sax;

import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;

public class Player extends DefaultHandler {

    private StringBuilder value;
    private String playerText;
    private int counter = 0;

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
                try {
                    counter++;
                    File file = new File("player" + counter + ".txt");
                    FileWriter writer = new FileWriter(file);
                    writer.write(playerText);
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                playerText = "";
                break;
            case "Name":
                System.out.println("Name: " + this.value.toString());
                playerText += "Name: " + this.value.toString() + "\n";
                break;
            case "Position":
                System.out.println("Position: " + this.value.toString());
                playerText += "Position: " + this.value.toString() + "\n";
                break;
            case "Nationality":
                System.out.println("Nationality: " + this.value.toString());
                playerText += "Nationality: " + this.value.toString() + "\n";
                break;
            case "Club":
                System.out.println("Club: " + this.value.toString());
                playerText += "Club: " + this.value.toString() + "\n";
                break;
        }
    }
}
