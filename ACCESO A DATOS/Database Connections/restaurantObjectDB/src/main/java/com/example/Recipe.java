package com.example;

import javax.persistence.*;

@Entity
public class Recipe {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String difficulty;

    @ManyToOne
    private Course course;

    public Recipe() {
    }

    public Recipe(String name, String difficulty) {
        this.name = name;
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}