package com.jaxb;

import javax.xml.bind.JAXBContext;//Import libraries
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;

public class Main {//Main class to run the JAXB parsing
    public static void main(String[] args) {
        try {
            File file = new File("recursos/players.xml");//Create a file object for the XML file
            JAXBContext context = JAXBContext.newInstance(FootballPlayers.class);//
            Unmarshaller unmarshall = context.createUnmarshaller();//Create unmarshaller object
            FootballPlayers players = (FootballPlayers) unmarshall.unmarshal(file);//Unmarshal the XML into footballPlayers object

            List<Player> playersList = players.getPlayer();//Get the list of player objects
            for (int i = 0; i < playersList.size(); i++) {//Loop over each player
                Player player = playersList.get(i);//Get the player 
                String playerText = "";//String to store info of one player
                try {
                    File filePlayer = new File(player.getName() + ".txt");//Create a file for player
                    FileWriter writer = new FileWriter(filePlayer);//Create a writer for the file
                    playerText += "Name: " + player.getName() + "\n";//Get player attributes for writing in file
                    playerText += "Position: " + player.getPosition() + "\n";
                    playerText += "Nationality: " + player.getNationality() + "\n";
                    playerText += "Club: " + player.getClub() + "\n";
                    writer.write(playerText);//Write the player info in the file
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(playerText);//Print player info to console
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}