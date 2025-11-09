package com.example;

public class recipe {
    private int id;
    private String name;
    private String description;
    private int difficulty;
    private int time;
    private int courseId; //Foreign key to Course

    public recipe() {

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

    public int getDifficulty() {
        return difficulty;
    }

    public int getTime() {
        return time;
    }

    public int getCourseId() {
        return courseId;
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

    public void setDifficulty(int difficultyValue) {
        this.difficulty = difficultyValue;
    }

    public void setTime(int timeValue) {
        this.time = timeValue;
    }

    public void setCourseId(int courseIdValue) {
        this.courseId = courseIdValue;
    }
}
