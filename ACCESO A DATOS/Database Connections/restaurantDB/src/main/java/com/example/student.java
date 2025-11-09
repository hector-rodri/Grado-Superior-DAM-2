package com.example;

public class student {
    private int id;
    private String name;
    private String surname;
    private int courseId; //Foreign key to Course

    public student() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
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

    public void setSurname(String surnameValue) {
        this.surname = surnameValue;
    }

    public void setCourseId(int courseIdValue) {
        this.courseId = courseIdValue;
    }
}
