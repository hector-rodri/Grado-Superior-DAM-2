package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class chef {
    private int id;
    private String name;
    private String speciality;

    public chef(String name, String speciality) {
        this.name = name;
        this.speciality = speciality;
    }

    public chef(int id, String name, String speciality) {
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

        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");
                PreparedStatement ps = connect.prepareStatement(insert)) {

            ps.setString(1, chef.getName());
            ps.setString(2, chef.getSpeciality());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<chef> getAllChefs() {
        List<chef> chefs = new ArrayList<>();
        String query = "SELECT * FROM Chef";

        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                chef chef = new chef(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("especialidad"));
                chefs.add(chef);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chefs;
    }

    public boolean updateChef(chef chef) {
        String update = "UPDATE Chef SET nombre = ?, especialidad = ? WHERE id = ?";

        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");
                PreparedStatement ps = connect.prepareStatement(update)) {

            ps.setString(1, chef.getName());
            ps.setString(2, chef.getSpeciality());
            ps.setInt(3, chef.getId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteChef(int chefId) {
        String delete = "DELETE FROM Chef WHERE id = ?";

        try (Connection connect = DriverManager.getConnection("jdbc:sqlite:cookschool.db");
                PreparedStatement ps = connect.prepareStatement(delete)) {

            ps.setInt(1, chefId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
