package com.example;

import java.sql.*;//Import all libraries
import java.util.*;

public class recipe {
    private int id;//Variables to store the recipe attributes
    private String name;
    private String description;
    private int difficulty;
    private int time;
    private int courseId;

    public recipe(String name, String description, int difficulty, int time, int courseId) {//Constructor without ID
        this.name = name;//Set the attributes
        this.description = description;
        this.difficulty = difficulty;
        this.time = time;
        this.courseId = courseId;

    }

    public recipe(int id, String name, String description, int difficulty, int time, int courseId) {//Constructor with ID
        this.id = id;//Set the attributes
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.time = time;
        this.courseId = courseId;
    }

    public int getId() {//Getters methods
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

    public void setId(int idValue) {//Setters methods
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

    public void insertRecipe(recipe recipe) {//Method to insert a recipe in the database
        String insert = "INSERT INTO Receta(curso_id, nombre, dificultad) VALUES (?, ?, ?)";//SQL query to insert a recipe

        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");//Connect to the database
                PreparedStatement ps = connect.prepareStatement(insert)) {//Prepare the query

            ps.setInt(1, recipe.getCourseId());//Set the parameters of the query
            ps.setString(2, recipe.getName());
            ps.setInt(3, recipe.getDifficulty());
            ps.executeUpdate();//Execute the query
        } catch (SQLException e) {//Catch any SQL exception
            e.printStackTrace();
        }
    }

    public List<recipe> getAllRecipes() {//Method to get all recipes from the database
        List<recipe> recipes = new ArrayList<>();//List to store the recipes
        String query = "SELECT * FROM Receta";//SQL query to get all recipes

        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");//Connect to the database
                Statement stmt = connect.createStatement();//Create a statement for executing the query
                ResultSet rs = stmt.executeQuery(query)) {//Execute the query and get the result set

            while (rs.next()) {//Iterate the result set
                recipe r = new recipe(//Create a new recipe object with the data from the result set
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("dificultad"),
                        rs.getInt("tiempo"),
                        rs.getInt("curso_id"));
                recipes.add(r);//Add the recipe to the list
            }
        } catch (SQLException e) {//Catch any SQL exception
            e.printStackTrace();
        }
        return recipes;//Return the list of recipes
    }

    public boolean updateRecipe(recipe recipe) {//Method to update a recipe in the database
        String update = "UPDATE Receta SET curso_id = ?, nombre = ?, dificultad = ? WHERE id = ?";//SQL query to update a recipe

        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");//Connect to the database
                PreparedStatement ps = connect.prepareStatement(update)) {//Prepare the query

            ps.setInt(1, recipe.getCourseId());//Set the parameters of the query
            ps.setString(2, recipe.getName());
            ps.setInt(3, recipe.getDifficulty());
            ps.setInt(4, recipe.getId());
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

    public boolean deleteRecipe(int recipeId) {//Method to delete a recipe from the database
        String delete = "DELETE FROM Receta WHERE id = ?";//SQL query to delete a recipe

        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");//Connect to the database
                PreparedStatement ps = connect.prepareStatement(delete)) {//Prepare the query

            ps.setInt(1, recipeId);//Set the parameter of the query
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
