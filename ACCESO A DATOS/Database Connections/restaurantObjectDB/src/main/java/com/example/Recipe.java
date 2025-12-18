package com.example;

import javax.persistence.*;//Import libraries

@Entity//Marks this class as a JPA entity, meaning it will be mapped to a database table
public class Recipe {
    @Id//Specifies that this field is the primary key of the entity
    @GeneratedValue//Indicates that the primary key value will be automatically generated
    private int id;

    private String name;
    private String difficulty;

    @ManyToOne//Many recipes can belong to one course
    private Course course;

    public Recipe() {//Default constructor
    }

    public Recipe(String name, String difficulty) {//Constructor initializing recipe with name and difficulty
        this.name = name;
        this.difficulty = difficulty;
    }

    public int getId() {//Getters and setters
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