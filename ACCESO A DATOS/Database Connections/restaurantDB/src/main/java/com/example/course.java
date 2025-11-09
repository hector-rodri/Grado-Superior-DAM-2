package com.example;

public class course {
    private int id;
    private String name;
    private String description;
    private int duration;
    private int chefId; //Foreign key to Chef

    public course() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public int getChefId() {
        return chefId;
    }

    public void setId(int idValue) {
        this.id = idValue;
    }

    public void setName(String nameValue) {
        this.name = nameValue;
    }

    public void setDescription(String descriptionValue) {
        this.description = descriptionValue;
    }

    public void setDuration(int durationValue) {
        this.duration = durationValue;
    }

    public void setChefId(int chefIdValue) {
        this.chefId = chefIdValue;
    }
}
