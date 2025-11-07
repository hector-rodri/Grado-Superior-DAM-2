package com.example;

public class chef {
    private int id;//PK
    private String name;
    private String speciality;

    public chef() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setId(int idValue) {
        this.id = idValue;
    }

    public void setName(String nameValue) {
        this.name = nameValue;
    }

    public void setSpeciality(String specialityValue) {
        this.speciality = specialityValue;
    }
}
