package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class course {
    private int id;
    private String name;
    private String description;
    private int duration;
    private int chefId;

    public course(String name, String description, int duration, int chefId) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.chefId = chefId;
    }

    public course(int id, String name, String description, int duration, int chefId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.chefId = chefId;
    }

    public int getId() {
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

    public void setId(int idValue) {
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

    public void insertCourse(course course) {
        String insert = "INSERT INTO Curso(nombre, descripcion, duracion, chef_id) VALUES (?, ?, ?, ?)";

        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");
                PreparedStatement ps = connect.prepareStatement(insert)) {

            ps.setString(1, course.getName());
            ps.setString(2, course.getDescription());
            ps.setInt(3, course.getDuration());
            ps.setInt(4, course.getChefId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<course> getAllCourses() {
        List<course> courses = new ArrayList<>();
        String query = "SELECT * FROM Curso";

        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                course c = new course(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("duracion"),
                        rs.getInt("chef_id"));
                courses.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public boolean updateCourse(course course) {
        String update = "UPDATE Curso SET nombre = ?, descripcion = ?, duracion = ?, chef_id = ? WHERE id = ?";
        int updated = 0;
        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");
                PreparedStatement ps = connect.prepareStatement(update)) {

            ps.setString(1, course.getName());
            ps.setString(2, course.getDescription());
            ps.setInt(3, course.getDuration());
            ps.setInt(4, course.getChefId());
            ps.setInt(5, course.getId());
            updated = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (updated > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void deleteCourse(int courseId) {
        String delete = "DELETE FROM Curso WHERE id = ?";

        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");
                PreparedStatement ps = connect.prepareStatement(delete)) {

            ps.setInt(1, courseId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
