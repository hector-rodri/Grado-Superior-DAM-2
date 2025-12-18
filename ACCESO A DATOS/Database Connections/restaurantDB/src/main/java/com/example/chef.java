package com.example;//Package name

import java.sql.*;//Import all libraries
import java.util.*;

public class chef {
    private int id;//Variables to store chef attributes
    private String name;
    private String speciality;

    public chef(String name, String speciality) {//Constructor without ID
        this.name = name;//Set the attributes
        this.speciality = speciality;
    }

    public chef(int id, String name, String speciality) {//Constructor with ID
        this.id = id;//Set the attributes
        this.name = name;
        this.speciality = speciality;
    }

    public int getId() {//Getter methods
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setId(int idValue) {//Setter methods
        this.id = idValue;
    }

    public void setName(String nameValue) {
        this.name = nameValue;
    }

    public void setSpeciality(String specialityValue) {
        this.speciality = specialityValue;
    }

    public void insertChef(chef chef) {//Method to insert a chef into the database
        String insert = "INSERT INTO Chef(nombre, especialidad) VALUES (?, ?)";//SQL query to insert a chef

        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");//Connect to the database
                PreparedStatement ps = connect.prepareStatement(insert)) {//Prepare the query

            ps.setString(1, chef.getName());//Set query parameters
            ps.setString(2, chef.getSpeciality());
            ps.executeUpdate();//Execute the query
        } catch (SQLException e) {//Catch any SQL exception
            System.out.println("Error inserting chef: " + e.getMessage());
        }
    }

    public List<chef> getAllChefs() {//Method to get all chefs from the database
        List<chef> chefs = new ArrayList<>();//List to store chefs
        String query = "SELECT * FROM Chef";//SQL query to get all chefs

        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");//Connect to the database
                Statement stmt = connect.createStatement();//Create a statement for the query
                ResultSet rs = stmt.executeQuery(query)) {//Execute the query and get results

            while (rs.next()) {//Iterate through results
                chef chef = new chef(//Create a new chef object with data from the result set
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("especialidad"));
                chefs.add(chef);//Add the chef to the list
            }
        } catch (SQLException e) {//Catch any SQL exception
            System.out.println("Error retrieving chefs: " + e.getMessage());
        }
        return chefs;//Return the list of chefs
    }

    public boolean updateChef(chef chef) {//Method to update a chef in the database
        String update = "UPDATE Chef SET nombre = ?, especialidad = ? WHERE id = ?";//SQL query to update a chef

        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");//Connect to the database
                PreparedStatement ps = connect.prepareStatement(update)) {//Prepare the query

            ps.setString(1, chef.getName());//Set query parameters
            ps.setString(2, chef.getSpeciality());
            ps.setInt(3, chef.getId());
            int rowsAffected = ps.executeUpdate();//Execute the query
            if (rowsAffected == 0) {//If no rows affected, return false
                return false;
            }
            return true;//Return true if successful
        } catch (SQLException e) {//Catch any SQL exception
            System.out.println("Error updating chef: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteChef(int chefId) {//Method to delete a chef from the database
        String delete = "DELETE FROM Chef WHERE id = ?";//SQL query to delete a chef

        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");//Connect to the database
                PreparedStatement ps = connect.prepareStatement(delete)) {//Prepare the query

            ps.setInt(1, chefId);//Set query parameter
            int rowsAffected = ps.executeUpdate();//Execute the query
            if (rowsAffected == 0) {//If no rows affected, return false
                return false;
            }
            return true;//Return true if successful
        } catch (SQLException e) {//Catch any SQL exception
            System.out.println("Error deleting chef: " + e.getMessage());
            return false;
        }
    }
}
    