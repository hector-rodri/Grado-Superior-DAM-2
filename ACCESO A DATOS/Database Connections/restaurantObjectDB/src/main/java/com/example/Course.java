package com.example;

import javax.persistence.*;//Import libraries
import java.util.*;

@Entity//Marks this class as a JPA entity, meaning it will be mapped to a database table
public class Course {
    @Id//Specifies that this field is the primary key of the entity
    @GeneratedValue//Indicates that the primary key value will be automatically generated
    private int id;

    private String name;
    private int duration;

    @ManyToOne//Many courses can be associated with one chef
    private Chef chef;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)//One-to-many relationship with recipes
    private List<Recipe> recipes;//List of recipes taught in the course

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)//One-to-many relationship with students
    private List<Student> students;//List of students enrolled in the course

    public Course() {//Default constructor
    }

    public Course(String name, int duration) {//Constructor initializing course with name and duration
        this.name = name;
        this.duration = duration;
        this.recipes = new ArrayList<>();//Initializes recipes list
        this.students = new ArrayList<>();//Initializes students list
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

    public void addRecipe(Recipe recipe) {//Adds a recipe and links it to this course
        this.recipes.add(recipe);
        recipe.setCourse(this);
    }

    public void addStudent(Student student) {//Adds a student and links them to this course
        this.students.add(student);
        student.setCourse(this);
    }
}
