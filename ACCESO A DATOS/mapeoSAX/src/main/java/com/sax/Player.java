package com.sax;

import org.xml.sax.*;//Import libraries
import org.xml.sax.helpers.*;
import java.io.*;

public class Player extends DefaultHandler {//Class that can handle XML events by extending DefaultHandler

    private StringBuilder value;//Store text from XML elements
    private String playerText = "";//Store the info player by player for write in the file
    private int counter = 0;//Counter for name files

    public Player() {
        this.value = new StringBuilder();//Creates a empty StringBuilder
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {//Method called when the parser reads text inside an XML element
        this.value.setLength(0);//Clear the current content of the StringBuilder
        this.value.append(ch, start, length);//Append new characters from the XML to value
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {//Method called when the parser read the end of an XML element
        switch (qName) {
            case "Player"://If the element is Player then print blank line and creates a file with player content 
                System.out.println("");
                try {
                    counter++;
                    File file = new File("player" + counter + ".txt");//Creates a new file
                    FileWriter writer = new FileWriter(file);//Creates a new file writer for write into the file
                    writer.write(playerText);//Writes into the file the String playerText with the player info 
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                playerText = "";//Clean the var for store new info of another player
                break;
            case "Name"://If the element is Name, print the name value and save it in playerText
                System.out.println("Name: " + this.value.toString());
                playerText += "Name: " + this.value.toString() + "\n";
                break;
            case "Position"://If the element is Position, print the position value and save it in playerText
                System.out.println("Position: " + this.value.toString());
                playerText += "Position: " + this.value.toString() + "\n";
                break;
            case "Nationality"://If the element is Nationality, print the nationality value and save it in playerText
                System.out.println("Nationality: " + this.value.toString());
                playerText += "Nationality: " + this.value.toString() + "\n";
                break;
            case "Club"://If the element is Club, print the club value and save it in playerText
                System.out.println("Club: " + this.value.toString());
                playerText += "Club: " + this.value.toString() + "\n";
                break;
        }
    }
}
