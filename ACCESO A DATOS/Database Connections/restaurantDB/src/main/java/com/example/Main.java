package com.example;

import java.sql.*;
import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Main m = new Main();
        m.createTables();

        while (true) {
            System.out.println("Welcome to the Cooking School Management System!");
            System.out.println("Choose an option:");
            System.out.println("1 - Manage chefs");
            System.out.println("2 - Manage courses");
            System.out.println("3 - Manage students");
            System.out.println("4 - Manage recipes");
            System.out.println("5 - Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    manageChefs();
                    break;
                case 2:
                    manageCourses();
                    break;
                case 3:
                    manageStudents();
                    break;
                case 4:
                    manageRecipes();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public void createTables() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:cookschool.db");
            Statement stmt = connection.createStatement();

            String createTableChef = "CREATE TABLE IF NOT EXISTS Chef (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," + "nombre TEXT NOT NULL," +
                    "especialidad TEXT" + ");";
            String createTableCurso = "CREATE TABLE IF NOT EXISTS Curso (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," + "nombre TEXT NOT NULL," +
                    "chef_id INTEGER," + "duracion INTEGER," + "FOREIGN KEY (chef_id) REFERENCES Chef(id)" + ");";
            String createTableReceta = "CREATE TABLE IF NOT EXISTS Receta (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," + "curso_id INTEGER," +
                    "nombre TEXT NOT NULL," + "dificultad TEXT," + "FOREIGN KEY (curso_id) REFERENCES Curso(id)" + ");";
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
    }

    private static void manageChefs() {
        chef chef = new chef("", "");
        while (true) {
            System.out.println("--Manage Chefs--");
            System.out.println("1 - Add chef");
            System.out.println("2 - View chefs");
            System.out.println("3 - Update chef");
            System.out.println("4 - Delete chef");
            System.out.println("5 - Back");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Speciality: ");
                    String speciality = sc.nextLine();
                    chef newChef = new chef(name, speciality);
                    newChef.insertChef(newChef);
                    System.out.println("Chef added");
                    break;
                case 2:
                    List<chef> listChefs = chef.getAllChefs();
                    if (listChefs.isEmpty()) {
                        System.out.println("No records found");
                    } else {
                        for (chef c : listChefs) {
                            System.out.println("ID: " + c.getId() + ", Name: " + c.getName() +
                                    ", Speciality: " + c.getSpeciality());
                        }
                    }
                    break;
                case 3:
                    System.out.print("ID to update: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New name: ");
                    String newName = sc.nextLine();
                    System.out.print("New speciality: ");
                    String newSpeciality = sc.nextLine();
                    chef updatedChef = new chef(id, newName, newSpeciality);
                    boolean updated = chef.updateChef(updatedChef);
                    if (!updated)
                        System.out.println("No records found");
                    else
                        System.out.println("Chef updated");
                    break;
                case 4:
                    System.out.print("ID to delete: ");
                    int deleteId = sc.nextInt();
                    boolean deleted = chef.deleteChef(deleteId);
                    if (!deleted)
                        System.out.println("No records found");
                    else
                        System.out.println("Chef deleted");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void manageCourses() {
        course course = new course("", "", 0, 0);
        while (true) {
            System.out.println("--Manage Courses--");
            System.out.println("1 - Add Course");
            System.out.println("2 - View Courses");
            System.out.println("3 - Update Course");
            System.out.println("4 - Delete Course");
            System.out.println("5 - Back");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Description: ");
                    String description = sc.nextLine();
                    System.out.print("Duration: ");
                    int duration = sc.nextInt();
                    System.out.print("Chef ID: ");
                    int chefId = sc.nextInt();
                    course newCourse = new course(name, description, duration, chefId);
                    course.insertCourse(newCourse);
                    break;
                case 2:
                    List<course> coursesList = course.getAllCourses();
                    if (coursesList.isEmpty()) {
                        System.out.println("No records found");
                    } else {
                        for (course c : coursesList) {
                            System.out.println("ID: " + c.getId() + ", Name: " + c.getName() +
                                    ", Description: " + c.getDescription() + ", Duration: " + c.getDuration() +
                                    ", Chef ID: " + c.getChefId());
                        }
                    }
                    break;
                case 3:
                    System.out.print("ID to update: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New name: ");
                    String newName = sc.nextLine();
                    System.out.print("New description: ");
                    String newDesc = sc.nextLine();
                    System.out.print("New duration: ");
                    int newDuration = sc.nextInt();
                    System.out.print("New Chef ID: ");
                    int newChefId = sc.nextInt();
                    course updatedCourse = new course(id, newName, newDesc, newDuration, newChefId);
                    boolean updated = course.updateCourse(updatedCourse);
                    if (!updated)
                        System.out.println("No records found");
                    else
                        System.out.println("Course updated");
                    break;
                case 4:
                    System.out.print("ID to delete: ");
                    int deleteId = sc.nextInt();
                    boolean deleted = course.deleteCourse(deleteId);
                    if (!deleted) {
                        System.out.println("No records found");
                    } else {
                        System.out.println("Course deleted");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void manageStudents() {
        student student = new student("", "", 0);
        while (true) {
            System.out.println("--Manage Students--");
            System.out.println("1 - Add Student");
            System.out.println("2 - View Students");
            System.out.println("3 - Update Student");
            System.out.println("4 - Delete Student");
            System.out.println("5 - Back");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Surname: ");
                    String surname = sc.nextLine();
                    System.out.print("Course ID: ");
                    int courseId = sc.nextInt();
                    student newStudent = new student(name, surname, courseId);
                    student.insertStudent(newStudent);
                    System.out.println("Student added");
                    break;
                case 2:
                    List<student> studentsList = student.getAllStudents();
                    if (studentsList.isEmpty()) {
                        System.out.println("No records found");
                    } else {
                        for (student s : studentsList) {
                            System.out.println("ID: " + s.getId() + ", Name: " + s.getName() +
                                    ", Surname: " + s.getSurname() + ", Course ID: " + s.getCourseId());
                        }
                    }
                    break;
                case 3:
                    System.out.print("ID to update: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New name: ");
                    String newName = sc.nextLine();
                    System.out.print("New surname: ");
                    String newSurname = sc.nextLine();
                    System.out.print("New course ID: ");
                    int newCourseId = sc.nextInt();
                    student updatedStudent = new student(id, newName, newSurname, newCourseId);
                    boolean updated = student.updateStudent(updatedStudent);
                    if (!updated)
                        System.out.println("No records found");
                    else
                        System.out.println("Student updated");
                case 4:
                    System.out.print("ID to delete: ");
                    int deleteId = sc.nextInt();
                    boolean deleted = student.deleteStudent(deleteId);
                    if (!deleted)
                        System.out.println("No records found");
                    else
                        System.out.println("Student deleted");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void manageRecipes() {
        recipe recipe = new recipe("", "", 0, 0, 0);
        while (true) {
            System.out.println("--Manage Recipes--");
            System.out.println("1 - Add Recipe");
            System.out.println("2 - View Recipes");
            System.out.println("3 - Update Recipe");
            System.out.println("4 - Delete Recipe");
            System.out.println("5 - Back");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    System.out.print("Difficulty (1-5): ");
                    int difficulty = sc.nextInt();
                    System.out.print("Time: ");
                    int time = sc.nextInt();
                    System.out.print("Course ID: ");
                    int courseId = sc.nextInt();
                    recipe newRecipe = new recipe(name, desc, difficulty, time, courseId);
                    recipe.insertRecipe(newRecipe);
                    System.out.println("Recipe added");
                    break;
                case 2:
                    List<recipe> recipesList = recipe.getAllRecipes();
                    if (recipesList.isEmpty()) {
                        System.out.println("No recipes found.");
                    } else {
                        for (recipe r : recipesList) {
                            System.out.println("ID: " + r.getId() + ", Name: " + r.getName() +
                                    ", Description: " + r.getDescription() + ", Difficulty: " + r.getDifficulty() +
                                    ", Time: " + r.getTime() + ", Course ID: " + r.getCourseId());
                        }
                    }
                    break;
                case 3:
                    System.out.print("ID to update: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New name: ");
                    String newName = sc.nextLine();
                    System.out.print("New description: ");
                    String newDesc = sc.nextLine();
                    System.out.print("New difficulty: ");
                    int newDifficulty = sc.nextInt();
                    System.out.print("New time: ");
                    int newTime = sc.nextInt();
                    System.out.print("New course ID: ");
                    int newCourseId = sc.nextInt();
                    recipe updatedRecipe = new recipe(id, newName, newDesc, newDifficulty, newTime, newCourseId);
                    boolean updated = recipe.updateRecipe(updatedRecipe);
                    if (!updated)
                        System.out.println("No records found");
                    else
                        System.out.println("Recipe updated");
                    break;
                case 4:
                    System.out.print("ID to delete: ");
                    int deleteId = sc.nextInt();
                    boolean deleted = recipe.deleteRecipe(deleteId);
                    if (!deleted)
                        System.out.println("No records found");
                    else
                        System.out.println("Recipe deleted");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}