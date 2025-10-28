package com.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("recursos/players.xml");
            JAXBContext context = JAXBContext.newInstance(FootballPlayers.class);
            Unmarshaller unmarshall = context.createUnmarshaller();
            FootballPlayers players = (FootballPlayers) unmarshall.unmarshal(file);

            List<Player> playersList = players.getPlayer();
            for (int i = 0; i < playersList.size(); i++) {
                Player player = playersList.get(i);
                String playerText = "";
                try {
                    File filePlayer = new File(player.getName() + ".txt");
                    FileWriter writer = new FileWriter(filePlayer);
                    playerText += "Name: " + player.getName() + "\n";
                    playerText += "Position: " + player.getPosition() + "\n";
                    playerText += "Nationality: " + player.getNationality() + "\n";
                    playerText += "Club: " + player.getClub() + "\n";
                    writer.write(playerText);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(playerText);
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}