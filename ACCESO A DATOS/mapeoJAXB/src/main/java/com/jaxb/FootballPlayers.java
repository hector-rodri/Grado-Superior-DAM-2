package com.jaxb;

import javax.xml.bind.annotation.XmlElement;//Import libraries
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "FootballPlayers")//This class represents the root <FootballPlayers> element in the XML
public class FootballPlayers {
    private List<Player> player;//List to store player elements

    @XmlElement(name = "Player")//Getter and setter for the player list
    public List<Player> getPlayer() {
        return player;
    }

    public void setPlayer(List<Player> player) {
        this.player = player;
    }
}
