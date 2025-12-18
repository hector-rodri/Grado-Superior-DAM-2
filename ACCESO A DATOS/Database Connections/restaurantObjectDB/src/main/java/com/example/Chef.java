package com.example;

import javax.persistence.*;//Import libraries
import java.util.*;

@Entity//Marks this class as a JPA entity, meaning it will be mapped to a database table
public class Chef {
    @Id//Specifies that this field is the primary key of the entity
    @GeneratedValue//Indicates that the primary key value will be automatically generated
    private int id;

    private String name;
    private String specialty;

    @OneToMany(mappedBy = "chef", cascade = CascadeType.ALL)//One-to-many relationship: one chef can have many courses
    private List<Course> courses;//List of courses

    public Chef() {//Default constructor
    }
    
    public Chef(String name, String specialty) {//Constructor initializing a chef with name and specialty
        this.name = name;
        this.specialty = specialty;
        this.courses = new ArrayList<>();//Initializes courses list
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    
    public void addCourse(Course course) {//Adds a course and associates it with this chef
        this.courses.add(course);
        course.setChef(this);
    }
}
