package com.example;

import java.io.*;//Import libraries
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class Main {
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

    public static String processDOM(Document doc) {//Method to process the doc and return content
        String[] node_data; //Declare an array to store data
        String exit = ""; 
        Node root = doc.getDocumentElement();//Get the root element of the xml <players>
        NodeList nodos = root.getChildNodes();//Get all child nodes of the root player elements

        for (int i = 0; i < nodos.getLength(); i++) {//Loop over players nodes 
            Node node = nodos.item(i);//Get the player node at index 
            if (node.getNodeType() == Node.ELEMENT_NODE) {//If the node is an element node
                node_data = processPlayer(node);//Process this player and get the info of the player
                exit += "\nName: " + node_data[0]; 
                exit += "\nPosition: " + node_data[1];
                exit += "\nNationality: " + node_data[2];
                exit += "\nClub: " + node_data[3];
                exit += "\n";
            }
        }
        return exit;//Return the info of all players
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
