package com.example;

import javax.persistence.*;
import java.util.*;

@Entity
public class Chef {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String specialty;

    @OneToMany(mappedBy = "chef", cascade = CascadeType.ALL)
    private List<Course> courses;

    public Chef() {
    }
    
    public Chef(String name, String specialty) {
        this.name = name;
        this.specialty = specialty;
        this.courses = new ArrayList<>();
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
    
    public void addCourse(Course course) {
        this.courses.add(course);
        course.setChef(this);
    }
}
