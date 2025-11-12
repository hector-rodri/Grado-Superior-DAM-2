package com.example;

import java.sql.*;//Import all libraries
import java.util.*;

public class student {
    private int id;//Variables to store the student attributes
    private String name;
    private String surname;
    private int courseId;

    public student(String name, String surname, int courseId) {//Constructor without ID
        this.name = name;//Set the attributes
        this.surname = surname;
        this.courseId = courseId;
    }

    public student(int id, String name, String surname, int courseId) {//Constructor with ID
        this.id = id;//Set the attributes
        this.name = name;
        this.surname = surname;
        this.courseId = courseId;
    }

    public int getId() {//Getters methods
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

    public void setId(int idValue) {//Setters methods
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

    public void insertStudent(student student) {//Method to insert a student in the database
        String insert = "INSERT INTO Student(name, surname, course_id) VALUES (?, ?, ?)";//SQL query to insert a student

        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");//Connect to the database
                PreparedStatement ps = connect.prepareStatement(insert)) {//Prepare the query

            ps.setString(1, student.getName());//Set the parameters of the query
            ps.setString(2, student.getSurname());
            ps.setInt(3, student.getCourseId());
            ps.executeUpdate();//Execute the query
        } catch (SQLException e) {//Catch any SQL exception
            e.printStackTrace();
        }
    }

    public List<student> getAllStudents() {//Method to get all students from the database
        List<student> students = new ArrayList<>();//List to store the students
        String query = "SELECT * FROM Student";//SQL query to get all students

        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");//Connect to the database
                Statement stmt = connect.createStatement();//Create a statement for executing the query
                ResultSet rs = stmt.executeQuery(query)) {//Execute the query and get the result set

            while (rs.next()) {//Iterate the result set
                student s = new student(//Create a new student object with the data from the result set
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("course_id"));
                students.add(s);//Add the student to the list
            }
        } catch (SQLException e) {//Catch any SQL exception
            e.printStackTrace();
        }
        return students;//Return the list of students
    }

    public boolean updateStudent(student student) {//Method to update a student in the database
        String update = "UPDATE Student SET name = ?, surname = ?, course_id = ? WHERE id = ?";//SQL query to update a student

        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");//Connect to the database
                PreparedStatement ps = connect.prepareStatement(update)) {//Prepare the query

            ps.setString(1, student.getName());//Set the parameters of the query
            ps.setString(2, student.getSurname());
            ps.setInt(3, student.getCourseId());
            ps.setInt(4, student.getId());
            int rowsAffected = ps.executeUpdate();//Execute the query and get the number of rows affected
            if (rowsAffected == 0) {//If no rows were affected, return false
                return false;
            }
            return true;//Return true if the update was successful
        } catch (SQLException e) {//Catch any SQL exception
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteStudent(int studentId) {//Method to delete a student from the database
        String delete = "DELETE FROM Student WHERE id = ?";//SQL query to delete a student

        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");//Connect to the database
                PreparedStatement ps = connect.prepareStatement(delete)) {//Prepare the query

            ps.setInt(1, studentId);//Set the parameter of the query
            int rowsAffected = ps.executeUpdate();//Execute the query and get the number of rows affected
            if (rowsAffected == 0) {//If no rows were affected, return false
                return false;
            }
            return true;//Return true if the deletion was successful
        } catch (SQLException e) {//Catch any SQL exception
            e.printStackTrace();
            return false;
        }
    }
}
