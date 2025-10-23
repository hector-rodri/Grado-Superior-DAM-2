package com.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Player")
public class Player {
    private String Name;
    private String Position;
    private String Nationality;
    private String Club;

    @XmlElement(name = "Name")
    public String getName() { return Name; }
    public void setName(String name) { this.Name = name; }

    @XmlElement(name = "Position")
    public String getPosition() { return Position; }
    public void setPosition(String position) { this.Position = position; }

    @XmlElement(name = "Nationality")
    public String getNationality() { return Nationality; }
    public void setNationality(String nationality) { this.Nationality = nationality; }

    @XmlElement(name = "Club")
    public String getClub() { return Club; }
    public void setClub(String club) { this.Club = club; }

    @Override
    public String toString() {
        return "Name: " + Name + "\nPosition: " + Position + "\nNationality: " + Nationality + "\nClub: " + Club + "\n";
    }
}