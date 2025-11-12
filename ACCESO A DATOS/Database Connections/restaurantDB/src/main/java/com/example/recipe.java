package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class recipe {
    private int id;
    private String name;
    private String description;
    private int difficulty;
    private int time;
    private int courseId;

    public recipe(String name, String description, int difficulty, int time, int courseId) {
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.time = time;
        this.courseId = courseId;

    }

    public recipe(int id, String name, String description, int difficulty, int time, int courseId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.time = time;
        this.courseId = courseId;
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

    public int getDifficulty() {
        return difficulty;
    }

    public int getTime() {
        return time;
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

    public void setDescription(String descriptionValue) {
        this.description = descriptionValue;
    }

    public void setDifficulty(int difficultyValue) {
        this.difficulty = difficultyValue;
    }

    public void setTime(int timeValue) {
        this.time = timeValue;
    }

    public void setCourseId(int courseIdValue) {
        this.courseId = courseIdValue;
    }

    public void insertRecipe(recipe recipe) {
        String insert = "INSERT INTO Receta(curso_id, nombre, dificultad) VALUES (?, ?, ?)";

        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");
             PreparedStatement ps = connect.prepareStatement(insert)) {

            ps.setInt(1, recipe.getCourseId());
            ps.setString(2, recipe.getName());
            ps.setInt(3, recipe.getDifficulty());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<recipe> getAllRecipes() {
        List<recipe> recipes = new ArrayList<>();
        String query = "SELECT * FROM Receta";

        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");
             Statement stmt = connect.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                recipe r = new recipe(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("dificultad"),
                        rs.getInt("tiempo"),
                        rs.getInt("curso_id")
                );
                recipes.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    public void updateRecipe(recipe recipe) {
        String update = "UPDATE Receta SET curso_id = ?, nombre = ?, dificultad = ? WHERE id = ?";

        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");
             PreparedStatement ps = connect.prepareStatement(update)) {

            ps.setInt(1, recipe.getCourseId());
            ps.setString(2, recipe.getName());
            ps.setInt(3, recipe.getDifficulty());
            ps.setInt(4, recipe.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecipe(int recipeId) {
        String delete = "DELETE FROM Receta WHERE id = ?";

        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");
             PreparedStatement ps = connect.prepareStatement(delete)) {

            ps.setInt(1, recipeId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
