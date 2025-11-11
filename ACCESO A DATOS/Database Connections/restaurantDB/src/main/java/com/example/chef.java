package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class chef {
    private int id;//PK
    private String name;
    private String speciality;

    public chef(String name, String speciality) {
        this.name = name;
        this.speciality = speciality;
    }

    public chef(int id,String name, String speciality) {
        this.id = id;
        this.name = name;
        this.speciality = speciality;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setId(int idValue) {
        this.id = idValue;
    }

    public void setName(String nameValue) {
        this.name = nameValue;
    }

    public void setSpeciality(String specialityValue) {
        this.speciality = specialityValue;
    }

    public void insertChef(chef chef) {
        String insert = "INSERT INTO Chef(nombre, especialidad) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:cookschool.db");
            PreparedStatement pstmt = connection.prepareStatement(insert)) {

            pstmt.setString(1, chef.getName());
            pstmt.setString(2, chef.getSpeciality());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<chef> getAllChefs() {
        List<chef> chefs = new ArrayList<>();
        String query = "SELECT * FROM Chef";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:cookschool.db");
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                chef chef = new chef(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("especialidad")
                );
                chefs.add(chef);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chefs;
    }

    public void updateChef(chef chef) {
        String update = "UPDATE Chef SET nombre = ?, especialidad = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:cookschool.db");
             PreparedStatement pstmt = connection.prepareStatement(update)) {

            pstmt.setString(1, chef.getName());
            pstmt.setString(2, chef.getSpeciality());
            pstmt.setInt(3, chef.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteChef(int chefId) {
        String delete = "DELETE FROM Chef WHERE id = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:cookschool.db");
             PreparedStatement pstmt = connection.prepareStatement(delete)) {

            pstmt.setInt(1, chefId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
