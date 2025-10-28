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
    public String getName() {
        return Name;
    }

    public void setName(String nameValue) {
        this.Name = nameValue;
    }

    @XmlElement(name = "Position")
    public String getPosition() {
        return Position;
    }

    public void setPosition(String positionValue) {
        this.Position = positionValue;
    }

    @XmlElement(name = "Nationality")
    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationalityValue) {
        this.Nationality = nationalityValue;
    }

    @XmlElement(name = "Club")
    public String getClub() {
        return Club;
    }

    public void setClub(String clubValue) {
        this.Club = clubValue;
    }
}