package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class student {
    private int id;
    private String name;
    private String surname;
    private int courseId; // Foreign key to Course

    public student(String name, String surname, int courseId) {
        this.name = name;
        this.surname = surname;
        this.courseId = courseId;
    }

    public student(int id, String name, String surname, int courseId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.courseId = courseId;
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

    public void insertStudent(student student) {
        String insert = "INSERT INTO Student(name, surname, course_id) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:cookschool.db");
             PreparedStatement pstmt = connection.prepareStatement(insert)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getSurname());
            pstmt.setInt(3, student.getCourseId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<student> getAllStudents() {
        List<student> students = new ArrayList<>();
        String query = "SELECT * FROM Student";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:cookschool.db");
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                student s = new student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("course_id")
                );
                students.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    public void updateStudent(student student) {
        String update = "UPDATE Student SET name = ?, surname = ?, course_id = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:cookschool.db");
             PreparedStatement pstmt = connection.prepareStatement(update)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getSurname());
            pstmt.setInt(3, student.getCourseId());
            pstmt.setInt(4, student.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int studentId) {
        String delete = "DELETE FROM Student WHERE id = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:cookschool.db");
             PreparedStatement pstmt = connection.prepareStatement(delete)) {

            pstmt.setInt(1, studentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
