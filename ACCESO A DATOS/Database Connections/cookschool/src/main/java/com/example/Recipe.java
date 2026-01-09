package com.example;

import jakarta.persistence.*;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String difficulty;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Recipe() {}

    public Recipe(String name, String difficulty, Course course) {
        this.name = name;
        this.difficulty = difficulty;
        this.course = course;
    }

    public int getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}