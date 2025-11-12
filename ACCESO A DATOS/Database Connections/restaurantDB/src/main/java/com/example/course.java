package com.example;//Package name

import java.sql.*;//Import all libraries
import java.util.*;

public class course {
    private int id;//Variables to store course attributes
    private String name;
    private String description;
    private int duration;
    private int chefId;

    public course(String name, String description, int duration, int chefId) {//Constructor without ID
        this.name = name;//Set the attributes
        this.description = description;
        this.duration = duration;
        this.chefId = chefId;
    }

    public course(int id, String name, String description, int duration, int chefId) {//Constructor with ID
        this.id = id;//Set the attributes
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.chefId = chefId;
    }

    public int getId() {//Getter methods
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public int getChefId() {
        return chefId;
    }

    public void setId(int idValue) {//Setter methods
        this.id = idValue;
    }

    public void setName(String nameValue) {
        this.name = nameValue;
    }

    public void setDescription(String descriptionValue) {
        this.description = descriptionValue;
    }

    public void setDuration(int durationValue) {
        this.duration = durationValue;
    }

    public void setChefId(int chefIdValue) {
        this.chefId = chefIdValue;
    }

    public void insertCourse(course course) {//Method to insert a course into the database
        String insert = "INSERT INTO Curso(nombre, descripcion, duracion, chef_id) VALUES (?, ?, ?, ?)";//SQL query to insert a course

        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");//Connect to the database
                PreparedStatement ps = connect.prepareStatement(insert)) {//Prepare the query

            ps.setString(1, course.getName());//Set query parameters
            ps.setString(2, course.getDescription());
            ps.setInt(3, course.getDuration());
            ps.setInt(4, course.getChefId());
            ps.executeUpdate();//Execute the query
        } catch (SQLException e) {//Catch any SQL exception
            e.printStackTrace();
        }
    }

    public List<course> getAllCourses() {//Method to get all courses from the database
        List<course> courses = new ArrayList<>();//List to store the courses
        String query = "SELECT * FROM Curso";//SQL query to get all courses

        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");//Connect to the database
                Statement stmt = connect.createStatement();//Create a statement for the query
                ResultSet rs = stmt.executeQuery(query)) {//Execute the query and get results

            while (rs.next()) {//Iterate through results
                course c = new course(//Create a new course object with data from the result set
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("duracion"),
                        rs.getInt("chef_id"));
                courses.add(c);//Add the course to the list
            }
        } catch (SQLException e) {//Catch any SQL exception
            e.printStackTrace();
        }
        return courses;//Return the list of courses
    }

    public boolean updateCourse(course course) {//Method to update a course in the database
        String update = "UPDATE Curso SET nombre = ?, descripcion = ?, duracion = ?, chef_id = ? WHERE id = ?";//SQL query to update a course
        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");//Connect to the database
                PreparedStatement ps = connect.prepareStatement(update)) {//Prepare the query

            ps.setString(1, course.getName());//Set query parameters
            ps.setString(2, course.getDescription());
            ps.setInt(3, course.getDuration());
            ps.setInt(4, course.getChefId());
            ps.setInt(5, course.getId());
            int rowsAffected = ps.executeUpdate();//Execute the query
            if (rowsAffected == 0) {//If no rows affected, return false
                return false;
            }
            return true;//Return true if successful
        } catch (SQLException e) {//Catch any SQL exception
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCourse(int courseId) {//Method to delete a course from the database
        String delete = "DELETE FROM Curso WHERE id = ?";//SQL query to delete a course

        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");//Connect to the database
                PreparedStatement ps = connect.prepareStatement(delete)) {//Prepare the query

            ps.setInt(1, courseId);//Set query parameter
            int rowsAffected = ps.executeUpdate();//Execute the query
            if (rowsAffected == 0) {//If no rows affected, return false
                return false;
            }
            return true;//Return true if successful
        } catch (SQLException e) {//Catch any SQL exception
            e.printStackTrace();
            return false;
        }
    }
}
