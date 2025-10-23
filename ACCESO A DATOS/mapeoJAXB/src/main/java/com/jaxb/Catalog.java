package com.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "FootballPlayers")
public class Catalog {
    private List<Player> player;

    @XmlElement(name = "Player")
    public List<Player> getPlayer() { return player; }
    public void setPlayer(List<Player> book) { this.player = book; }
}
