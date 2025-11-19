package com.example;

import javax.persistence.*;
import java.util.*;

@Entity
public class Course {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private int duration;

    @ManyToOne
    private Chef chef;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Recipe> recipes;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Student> students;

    public Course() {
    }

    public Course(String name, int duration) {
        this.name = name;
        this.duration = duration;
        this.recipes = new ArrayList<>();
        this.students = new ArrayList<>();
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
        recipe.setCourse(this);
    }

    public void addStudent(Student student) {
        this.students.add(student);
        student.setCourse(this);
    }
}