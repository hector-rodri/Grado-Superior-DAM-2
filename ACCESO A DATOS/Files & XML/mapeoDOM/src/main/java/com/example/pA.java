package com.example;

import java.io.*;//Import libraries
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class pA {
    public static void main(String[] args) {
        File file = new File("players.xml");//Create a file object for xml
        open_XML_DOM(file);//Call method to open and parse the XML file
    }

    public static int open_XML_DOM(File file) {//Method that opens xml and returns an int status
        Document doc = null;
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            docBuilderFactory.setIgnoringComments(true);//Ignore xml comments
            docBuilderFactory.setIgnoringElementContentWhitespace(true);//Ignore blank elements
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            doc = docBuilder.parse(file);//Parse the file into a doc
            String exit = processDOM(doc);//Call method to read the doc and get a text
            System.out.println(exit);
            return 0;//Success
        } catch (Exception e) {
            e.printStackTrace();//Error details
            return -1;//Error
        }
    }

    public static String processDOM(Document doc) {//Method to process the doc, return content and create files
        Node root = doc.getDocumentElement();//Get the root element of the xml players
        NodeList nodos = root.getChildNodes();//Get child nodes of the root player elements
        String exit = "";//String to store all players info for console output

        for (int i = 0; i < nodos.getLength(); i++) { //Loop over players nodes
            Node node = nodos.item(i);//Get the player node at index
            if (node.getNodeType() == Node.ELEMENT_NODE) { //If the node is an element node
                String[] playerInfo = processPlayer(node); //Process this player and get the info of the player
                String playerText = "";//String to store info of one player
                playerText += "Name: " + playerInfo[0] + "\n";
                playerText += "Position: " + playerInfo[1] + "\n";
                playerText += "Nationality: " + playerInfo[2] + "\n";
                playerText += "Club: " + playerInfo[3] + "\n";
                exit += playerText + "\n";//Append the player info to the exit string for console out

                File file = new File(playerInfo[0] + ".txt");//Create a file object for txt with player name
                try {
                    FileWriter writer = new FileWriter(file);
                    writer.write(playerText);//Write the player info in the file
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return exit;//Return all players info
    }

    public static String[] processPlayer(Node node) {//Method to read one player node and return content
        String[] data = new String[4];//Create a array of 4 strings to store stats of the player
        Node nodeTemp = null;//Temporary node variable
        int count = 0;//Counter

        NodeList nodes = node.getChildNodes();//Get child nodes of the player like name,position...
        for (int i = 0; i < nodes.getLength(); i++) {//Loop over the child nodes
            nodeTemp = nodes.item(i);//Current child node
            if (nodeTemp.getNodeType() == Node.ELEMENT_NODE) {//If child is an element node
                data[count] = nodeTemp.getTextContent();//Store the text content of the element in the array
                count++;
            }
        }
        return data;//Return the array with the player info
    }
}
