package com.jaxb;

import javax.xml.bind.annotation.XmlElement;//Import libraries
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Player")
public class Player {//This class represents one <Player> element in the XML
    private String Name;//Attributes of the player element
    private String Position;
    private String Nationality;
    private String Club;

    @XmlElement(name = "Name")//Getters and setters for each attribute
    public String getName() {
        return Name;
    }

    public void setName(String nameValue) {
        this.Name = nameValue;
    }

    @XmlElement(name = "Position")//Getters and setters for each attribute
    public String getPosition() {
        return Position;
    }

    public void setPosition(String positionValue) {
        this.Position = positionValue;
    }

    @XmlElement(name = "Nationality")//Getters and setters for each attribute
    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationalityValue) {
        this.Nationality = nationalityValue;
    }

    @XmlElement(name = "Club")//Getters and setters for each attribute
    public String getClub() {
        return Club;
    }

    public void setClub(String clubValue) {
        this.Club = clubValue;
    }
}