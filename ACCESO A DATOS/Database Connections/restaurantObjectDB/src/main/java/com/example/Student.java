package com.example;

import javax.persistence.*;//Import libraries

@Entity//Marks this class as a JPA entity, meaning it will be mapped to a database table
public class Student {

    @Id//Specifies that this field is the primary key of the entity
    @GeneratedValue//Indicates that the primary key value will be automatically generated
    private int id;

    private String name;

    @ManyToOne//Specifies a many-to-one relationship: many students can belong to one course
    private Course course;

    public Student() { //Default constructor
    }

    public Student(String name) {//Constructor that initializes a student with a name
        this.name = name;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}