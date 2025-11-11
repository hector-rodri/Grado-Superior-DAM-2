package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:cookschool.db");
            Statement stmt = connection.createStatement();

            String createTableChef = "CREATE TABLE IF NOT EXISTS Chef (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +"nombre TEXT NOT NULL," +
                    "especialidad TEXT" + ");";
            String createTableCurso = "CREATE TABLE IF NOT EXISTS Curso (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," + "nombre TEXT NOT NULL," +
                    "chef_id INTEGER," + "duracion INTEGER," + "FOREIGN KEY (chef_id) REFERENCES Chef(id)" +");";
            String createTableReceta = "CREATE TABLE IF NOT EXISTS Receta (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," + "curso_id INTEGER," +
                    "nombre TEXT NOT NULL," + "dificultad TEXT," + "FOREIGN KEY (curso_id) REFERENCES Curso(id)" +");";
            String createTableAlumno = "CREATE TABLE IF NOT EXISTS Alumno (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," + "nombre TEXT NOT NULL," +
                    "curso_id INTEGER," + "FOREIGN KEY (curso_id) REFERENCES Curso(id)" + ");";

            stmt.execute(createTableChef);
            stmt.execute(createTableCurso);
            stmt.execute(createTableReceta);
            stmt.execute(createTableAlumno);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true) {
            Scanner sc = new Scanner(System.in);

            System.out.println("Welcome to the Cooking School Management System!");
            System.out.println("Choose an option:");
            System.out.println("1 - Add a new record");
            System.out.println("2 - View existing records");
            System.out.println("3 - Update a record");
            System.out.println("4 - Delete a record");
            System.out.println("5 - Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option");
            }

        }

    }
}