package com.example;//Package name

import java.sql.*;//Import all libraries
import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);//Scanner for user input

    public static void main(String[] args) {
        Main m = new Main();
        m.createTables();//Create database tables if they don't exist

        while (true) {//Loop for the main menu
            System.out.println("Welcome to the Cooking School Management System!");
            System.out.println("Choose an option:");//Display menu options
            System.out.println("1 - Manage chefs");
            System.out.println("2 - Manage courses");
            System.out.println("3 - Manage students");
            System.out.println("4 - Manage recipes");
            System.out.println("5 - Exit");
            int choice = sc.nextInt();//Read user choice

            switch (choice) {//Switch case for menu options
                case 1:
                    manageChefs();//Call method to manage chefs
                    break;
                case 2:
                    manageCourses();//Call method to manage courses
                    break;
                case 3:
                    manageStudents();//Call method to manage students
                    break;
                case 4:
                    manageRecipes();//Call method to manage recipes
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;//Exit the program
                default:
                    System.out.println("Invalid option");//If user enters invalid option
            }
        }
    }

    public void createTables() {//Method to create database tables
        try {
            Class.forName("org.sqlite.JDBC");//Load SQLite JDBC driver
            Connection connection = DriverManager.getConnection("jdbc:sqlite:cookschool.db");//Connect to the database
            Statement stmt = connection.createStatement();//Create SQL statement
            //SQL queries to create tables if they don't exist
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

            stmt.execute(createTableChef);//Execute table creation queries
            stmt.execute(createTableCurso);
            stmt.execute(createTableReceta);
            stmt.execute(createTableAlumno);
            connection.close();//Close database connection
        } catch (Exception e) {//Catch any exceptions
            e.printStackTrace();
        }
    }

    private static void manageChefs() {//Method to manage chefs
        chef chef = new chef("", "");//Create chef object
        while (true) {//Loop for chef management menu
            System.out.println("--Manage Chefs--");
            System.out.println("1 - Add chef");
            System.out.println("2 - View chefs");
            System.out.println("3 - Update chef");
            System.out.println("4 - Delete chef");
            System.out.println("5 - Back");
            int choice = sc.nextInt();//Read user choice

            switch (choice) {//Switch case for chef menu
                case 1://Add a new chef
                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();//Get chef name
                    System.out.print("Speciality: ");
                    String speciality = sc.nextLine();//Get chef speciality
                    chef newChef = new chef(name, speciality);//Create new chef
                    newChef.insertChef(newChef);//Insert into database
                    System.out.println("Chef added");
                    break;
                case 2://View chefs
                    List<chef> listChefs = chef.getAllChefs();//Get chefs from database
                    if (listChefs.isEmpty()) {//If no chefs found
                        System.out.println("No records found");
                    } else {//Show all chefs
                        for (chef c : listChefs) {//Iterate chefs list
                            System.out.println("ID: " + c.getId() + ", Name: " + c.getName() +
                                    ", Speciality: " + c.getSpeciality());
                        }
                    }
                    break;
                case 3://Update chef
                    System.out.print("ID to update: ");
                    int id = sc.nextInt();//Get chef ID
                    sc.nextLine();
                    System.out.print("New name: ");
                    String newName = sc.nextLine();//Get new name
                    System.out.print("New speciality: ");
                    String newSpeciality = sc.nextLine();//Get new speciality
                    chef updatedChef = new chef(id, newName, newSpeciality);//Create updated chef
                    boolean updated = chef.updateChef(updatedChef);//Update in database
                    if (!updated) {//If no record found
                        System.out.println("No records found");
                    } else {
                        System.out.println("Chef updated");
                    }
                    break;
                case 4://Delete chef
                    System.out.print("ID to delete: ");
                    int deleteId = sc.nextInt();//Get ID to delete
                    boolean deleted = chef.deleteChef(deleteId);//Delete from database
                    if (!deleted) {//If not found
                        System.out.println("No records found");
                    } else {
                        System.out.println("Chef deleted");
                    }
                    break;
                case 5:
                    return;//Go back to main menu
                default:
                    System.out.println("Invalid option");//If user enters invalid option
            }
        }
    }

    private static void manageCourses() {//Method to manage courses
        course course = new course("", "", 0, 0);//Create course object
        while (true) {//Loop for course management menu
            System.out.println("--Manage Courses--");
            System.out.println("1 - Add Course");
            System.out.println("2 - View Courses");
            System.out.println("3 - Update Course");
            System.out.println("4 - Delete Course");
            System.out.println("5 - Back");
            int choice = sc.nextInt();//Read user choice

            switch (choice) {//Switch case for course menu
                case 1://Add new course
                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();//Get course name
                    System.out.print("Description: ");
                    String description = sc.nextLine();//Get description
                    System.out.print("Duration: ");
                    int duration = sc.nextInt();//Get duration
                    System.out.print("Chef ID: ");
                    int chefId = sc.nextInt();//Get chef ID
                    course newCourse = new course(name, description, duration, chefId);//Create new course
                    course.insertCourse(newCourse);//Insert into database
                    System.out.println("Course added");
                    break;
                case 2://View all courses
                    List<course> coursesList = course.getAllCourses();//Get courses
                    if (coursesList.isEmpty()) {//If no courses found
                        System.out.println("No records found");
                    } else {//Show all courses
                        for (course c : coursesList) {//Iterate courses list
                            System.out.println("ID: " + c.getId() + ", Name: " + c.getName() +
                                    ", Description: " + c.getDescription() + ", Duration: " + c.getDuration() +
                                    ", Chef ID: " + c.getChefId());
                        }
                    }
                    break;
                case 3://Update course
                    System.out.print("ID to update: ");
                    int id = sc.nextInt();//Get course ID
                    sc.nextLine();
                    System.out.print("New name: ");
                    String newName = sc.nextLine();//Get new name
                    System.out.print("New description: ");
                    String newDesc = sc.nextLine();//Get new description
                    System.out.print("New duration: ");
                    int newDuration = sc.nextInt();//Get new duration
                    System.out.print("New Chef ID: ");
                    int newChefId = sc.nextInt();//Get new chef ID
                    course updatedCourse = new course(id, newName, newDesc, newDuration, newChefId);//Create updated course
                    boolean updated = course.updateCourse(updatedCourse);//Update in database
                    if (!updated){//If no record found
                        System.out.println("No records found");
                    } else{ 
                        System.out.println("Course updated"); 
                    }
                    break;
                case 4://Delete course
                    System.out.print("ID to delete: ");
                    int deleteId = sc.nextInt();//Get ID to delete
                    boolean deleted = course.deleteCourse(deleteId);//Delete from database
                    if (!deleted) {//If not found
                        System.out.println("No records found");
                    } else {
                        System.out.println("Course deleted");
                    }
                    break;
                case 5:
                    return;//Go back to main menu
                default:
                    System.out.println("Invalid option");//If invalid option
            }
        }
    }

    private static void manageStudents() {//Method to manage students
        student student = new student("", "", 0);//Create student object
        while (true) {//Loop for student management menu
            System.out.println("--Manage Students--");
            System.out.println("1 - Add Student");
            System.out.println("2 - View Students");
            System.out.println("3 - Update Student");
            System.out.println("4 - Delete Student");
            System.out.println("5 - Back");
            int choice = sc.nextInt();//Read user choice

            switch (choice) {//Switch case for student menu
                case 1://Add student
                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();//Get name
                    System.out.print("Surname: ");
                    String surname = sc.nextLine();//Get surname
                    System.out.print("Course ID: ");
                    int courseId = sc.nextInt();//Get course ID
                    student newStudent = new student(name, surname, courseId);//Create new student
                    student.insertStudent(newStudent);//Insert into database
                    System.out.println("Student added");
                    break;
                case 2://View students
                    List<student> studentsList = student.getAllStudents();//Get students
                    if (studentsList.isEmpty()) {//If no students found
                        System.out.println("No records found");
                    } else {//Show all students
                        for (student s : studentsList) {//Iterate students list
                            System.out.println("ID: " + s.getId() + ", Name: " + s.getName() +
                                    ", Surname: " + s.getSurname() + ", Course ID: " + s.getCourseId());
                        }
                    }
                    break;
                case 3://Update student
                    System.out.print("ID to update: ");
                    int id = sc.nextInt();//Get ID
                    sc.nextLine();
                    System.out.print("New name: ");
                    String newName = sc.nextLine();//Get new name
                    System.out.print("New surname: ");
                    String newSurname = sc.nextLine();//Get new surname
                    System.out.print("New course ID: ");
                    int newCourseId = sc.nextInt();//Get new course ID
                    student updatedStudent = new student(id, newName, newSurname, newCourseId);//Create updated student
                    boolean updated = student.updateStudent(updatedStudent);//Update in database
                    if (!updated){//If no record found
                        System.out.println("No records found");
                    } else {
                        System.out.println("Student updated");
                    }
                    break;
                case 4://Delete student
                    System.out.print("ID to delete: ");
                    int deleteId = sc.nextInt();//Get ID
                    boolean deleted = student.deleteStudent(deleteId);//Delete from database
                    if (!deleted){//If not found
                        System.out.println("No records found");
                    } else {
                        System.out.println("Student deleted");
                    }
                    break;
                case 5:
                    return;//Go back to main menu
                default:
                    System.out.println("Invalid option");//If invalid option
            }
        }
    }

    private static void manageRecipes() {//Method to manage recipes
        recipe recipe = new recipe("", "", 0, 0, 0);//Create recipe object
        while (true) {//Loop for recipe management menu
            System.out.println("--Manage Recipes--");
            System.out.println("1 - Add Recipe");
            System.out.println("2 - View Recipes");
            System.out.println("3 - Update Recipe");
            System.out.println("4 - Delete Recipe");
            System.out.println("5 - Back");
            int choice = sc.nextInt();//Read user choice

            switch (choice) {//Switch case for recipe menu
                case 1://Add recipe
                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();//Get recipe name
                    System.out.print("Description: ");
                    String desc = sc.nextLine();//Get description
                    System.out.print("Difficulty (1-5): ");
                    int difficulty = sc.nextInt();//Get difficulty
                    System.out.print("Time: ");
                    int time = sc.nextInt();//Get time
                    System.out.print("Course ID: ");
                    int courseId = sc.nextInt();//Get course ID
                    recipe newRecipe = new recipe(name, desc, difficulty, time, courseId);//Create new recipe
                    recipe.insertRecipe(newRecipe);//Insert into database
                    System.out.println("Recipe added");//Confirm message
                    break;
                case 2://View recipes
                    List<recipe> recipesList = recipe.getAllRecipes();//Get recipes
                    if (recipesList.isEmpty()) {//If no recipes found
                        System.out.println("No recipes found.");
                    } else {//Show all recipes
                        for (recipe r : recipesList) {//Iterate recipes list
                            System.out.println("ID: " + r.getId() + ", Name: " + r.getName() +
                                    ", Description: " + r.getDescription() + ", Difficulty: " + r.getDifficulty() +
                                    ", Time: " + r.getTime() + ", Course ID: " + r.getCourseId());
                        }
                    }
                    break;
                case 3://Update recipe
                    System.out.print("ID to update: ");
                    int id = sc.nextInt();//Get ID
                    sc.nextLine();
                    System.out.print("New name: ");
                    String newName = sc.nextLine();//Get new name
                    System.out.print("New description: ");
                    String newDesc = sc.nextLine();//Get new description
                    System.out.print("New difficulty: ");
                    int newDifficulty = sc.nextInt();//Get new difficulty
                    System.out.print("New time: ");
                    int newTime = sc.nextInt();//Get new time
                    System.out.print("New course ID: ");
                    int newCourseId = sc.nextInt();//Get new course ID
                    recipe updatedRecipe = new recipe(id, newName, newDesc, newDifficulty, newTime, newCourseId);//Create updated recipe
                    boolean updated = recipe.updateRecipe(updatedRecipe);//Update in database
                    if (!updated){//If no record found
                        System.out.println("No records found");
                    }
                    else{//If successful
                        System.out.println("Recipe updated");
                    }
                    break;
                case 4://Delete recipe
                    System.out.print("ID to delete: ");
                    int deleteId = sc.nextInt();//Get ID
                    boolean deleted = recipe.deleteRecipe(deleteId);//Delete from database
                    if (!deleted){//If not found
                        System.out.println("No records found");
                    }
                    else{//If successful
                        System.out.println("Recipe deleted");
                    }
                    break;
                case 5:
                    return;//Go back to main menu
                default:
                    System.out.println("Invalid option");//If invalid option
            }
        }
    }
}