package com.example;

import javax.persistence.*;//Import all libraries
import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);//Scanner for user input
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb_restaurant");//Create EntityManagerFactory for ObjectDB database
        EntityManager em = emf.createEntityManager();//Create EntityManager for database operations

        while (true) {
            System.out.println("Welcome to the Cooking School Management System!");//Menu options
            System.out.println("Choose an option:");
            System.out.println("1 - Manage chefs");
            System.out.println("2 - Manage courses");
            System.out.println("3 - Manage students");
            System.out.println("4 - Manage recipes");
            System.out.println("5 - Exit");
            int choice = sc.nextInt();

            switch (choice) {//Switch case for menu options
                case 1:
                    manageChefs(em);//Method to manage chefs
                    break;
                case 2:
                    manageCourses(em);//Method to manage courses
                    break;
                case 3:
                    manageStudents(em);//Method to manage students
                    break;
                case 4:
                    manageRecipes(em);//Method to manage recipes
                    break;
                case 5:
                    em.close();//When exit is choosen, close em and emf
                    emf.close();
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void manageChefs(EntityManager em) {//Method to manage chefs
        while (true) {
            System.out.println("--Manage Chefs--");//Menu options for chefs
            System.out.println("1 - Add chef");
            System.out.println("2 - View chefs");
            System.out.println("3 - Update chef");
            System.out.println("4 - Delete chef");
            System.out.println("5 - Back");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {//Switch case for menu options
                case 1://Insert chef
                    System.out.print("Enter chef name: ");//Add chef information
                    String name = sc.nextLine();
                    System.out.print("Enter specialty: ");
                    String specialty = sc.nextLine();
                    em.getTransaction().begin();//Begin transaction
                    Chef chef = new Chef(name, specialty);//Create new chef object for persist into database 
                    em.persist(chef);//Persist chef object
                    em.getTransaction().commit();//Commit transaction
                    System.out.println("Chef added");
                    break;
                case 2://View chefs
                    List<Chef> chefs = em.createQuery("SELECT c FROM Chef c", Chef.class).getResultList();//Get all chefs from database
                    if (chefs.isEmpty()) {//If no chefs found
                        System.out.println("Chefs not found");
                    }
                    for (Chef c : chefs) {//Print chef information 
                        System.out.println("ID: " + c.getId() + ", Name: " + c.getName() + ", Specialty: " + c.getSpecialty());
                    }
                    break;
                case 3://Update chef
                    System.out.print("Enter chef ID to update: ");//Get chef ID to update
                    long idUpdate = sc.nextLong();
                    sc.nextLine();
                    Chef chefToUpdate = em.find(Chef.class, idUpdate);//Get chef object from database for check if exists
                    if (chefToUpdate != null) {//If chef exists
                        System.out.print("Enter new name: ");//Get new information
                        String newName = sc.nextLine();
                        System.out.print("Enter new specialty: ");
                        String newSpecialty = sc.nextLine();
                        em.getTransaction().begin();//Begin transaction
                        chefToUpdate.setName(newName);//Update chef information
                        chefToUpdate.setSpecialty(newSpecialty);
                        em.getTransaction().commit();//Commit transaction
                        System.out.println("Chef updated");
                    } else {
                        System.out.println("Chef not found");
                    }
                    break;
                case 4://Delete chef
                    System.out.print("Enter chef ID to delete: ");//Get chef ID to delete
                    long idDelete = sc.nextLong();
                    sc.nextLine();
                    Chef chefToDelete = em.find(Chef.class, idDelete);//Get chef object from database for check if exists
                    if (chefToDelete != null) {//If chef exists
                        em.getTransaction().begin();//Begin transaction
                        em.remove(chefToDelete);//Delete chef object
                        em.getTransaction().commit();//Commit transaction
                        System.out.println("Chef deleted");
                    } else {
                        System.out.println("Chef not found");
                    }
                    break;
                case 5://Back to main menu
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void manageCourses(EntityManager em) {//Method to manage courses
        while (true) {
            System.out.println("--Manage Courses--");//Menu options for courses
            System.out.println("1 - Add course");
            System.out.println("2 - View courses");
            System.out.println("3 - Update course");
            System.out.println("4 - Delete course");
            System.out.println("5 - Back");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {//Switch case for menu options
                case 1://Add course
                    System.out.print("Enter course name: ");//Get course information
                    String courseName = sc.nextLine();
                    System.out.print("Enter duration: ");
                    int duration = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter chef ID for this course: ");//Get chef ID for this course
                    long chefId = sc.nextLong();
                    sc.nextLine();
                    Chef chef = em.find(Chef.class, chefId);//Get chef object from database for check if exists
                    if (chef != null) {//If chef exists
                        em.getTransaction().begin();//Begin transaction
                        Course course = new Course(courseName, duration);//Create new course object for persist into database
                        course.setChef(chef);//Set chef for this course
                        em.persist(course);//Persist course object
                        em.getTransaction().commit();//Commit transaction
                        System.out.println("Course added");
                    } else {
                        System.out.println("Chef not found");
                    }
                    break;
                case 2://View courses
                    List<Course> courses = em.createQuery("SELECT c FROM Course c", Course.class).getResultList();//Get all courses from database
                    if (courses.isEmpty()) {//If no courses found
                        System.out.println("Courses not found");
                    }
                    for (Course c : courses) {//Print course information
                        System.out.println(
                                "ID: " + c.getId() + ", Name: " + c.getName() + ", Duration: " + c.getDuration() +", Chef: " + c.getChef().getName());
                    }
                    break;
                case 3://Update course
                    System.out.print("Enter course ID to update: ");//Get course ID to update
                    int idUpdate = sc.nextInt();
                    sc.nextLine();
                    Course courseToUpdate = em.find(Course.class, idUpdate);//Get course object from database for check if exists
                    if (courseToUpdate != null) {//If course exists
                        System.out.print("Enter new name: ");//Get new information
                        String newName = sc.nextLine();
                        System.out.print("Enter new duration: ");
                        int newDuration = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter new chef ID: ");//Get new chef ID
                        long newChefId = sc.nextLong();
                        sc.nextLine();
                        Chef newChef = em.find(Chef.class, newChefId);//Get chef object from database for check if exists
                        em.getTransaction().begin();//Begin transaction
                        courseToUpdate.setName(newName);//Update course information
                        courseToUpdate.setDuration(newDuration);
                        if (newChef != null) {//If chef exists
                            courseToUpdate.setChef(newChef);//Update chef for this course
                        } else{
                            System.out.println("Chef not found");
                        }
                        em.getTransaction().commit();//Commit transaction
                        System.out.println("Course updated");
                    } else {
                        System.out.println("Course not found");
                    }
                    break;
                case 4://Delete course
                    System.out.print("Enter course ID to delete: ");//Get course ID to delete
                    int idDelete = sc.nextInt();
                    sc.nextLine();
                    Course courseToDelete = em.find(Course.class, idDelete);//Get course object from database for check if exists
                    if (courseToDelete != null) {//If course exists
                        em.getTransaction().begin();//Begin transaction
                        em.remove(courseToDelete);//Delete course object
                        em.getTransaction().commit();//Commit transaction
                        System.out.println("Course deleted");
                    } else {
                        System.out.println("Course not found");
                    }
                    break;
                case 5://Back to main menu
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void manageRecipes(EntityManager em) {//Method to manage recipes
        while (true) {
            System.out.println("--Manage Recipes--");//Menu options for recipes
            System.out.println("1 - Add recipe");
            System.out.println("2 - View recipes");
            System.out.println("3 - Update recipe");
            System.out.println("4 - Delete recipe");
            System.out.println("5 - Back");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {//Switch case for menu options
                case 1://Add recipe
                    System.out.print("Enter recipe name: ");//Get recipe information
                    String recipeName = sc.nextLine();
                    System.out.print("Enter difficulty: ");
                    String difficulty = sc.nextLine();
                    System.out.print("Enter course ID for this recipe: ");//Get course ID for this recipe
                    int courseId = sc.nextInt();
                    sc.nextLine();
                    Course course = em.find(Course.class, courseId);//Get course object from database for check if exists
                    if (course != null) {//If course exists
                        em.getTransaction().begin();//Begin transaction
                        Recipe recipe = new Recipe(recipeName, difficulty);//Create new recipe object for persist into database
                        recipe.setCourse(course);//Set course for this recipe
                        em.persist(recipe);//Persist recipe object
                        em.getTransaction().commit();//Commit transaction
                        System.out.println("Recipe added");
                    } else {
                        System.out.println("Course not found");
                    }
                    break;
                case 2://View recipes
                    List<Recipe> recipes = em.createQuery("SELECT r FROM Recipe r", Recipe.class).getResultList();//Get all recipes from database
                    if (recipes.isEmpty()) {//If no recipes found
                        System.out.println("Recipes not found");
                    }
                    for (Recipe r : recipes) {//Print recipe information
                        System.out.println("ID: " + r.getId() + ", Name: " + r.getName() +", Difficulty: " + r.getDifficulty() +", Course: " + r.getCourse().getName());
                    }
                    break;
                case 3://Update recipe
                    System.out.print("Enter recipe ID to update: ");//Get recipe ID to update
                    int idUpdate = sc.nextInt();
                    sc.nextLine();
                    Recipe recipeToUpdate = em.find(Recipe.class, idUpdate);//Get recipe object from database for check if exists
                    if (recipeToUpdate != null) {//If recipe exists
                        System.out.print("Enter new name: ");//Get new information
                        String newName = sc.nextLine();
                        System.out.print("Enter new difficulty: ");
                        String newDifficulty = sc.nextLine();
                        System.out.print("Enter new course ID: ");
                        int newCourseId = sc.nextInt();
                        sc.nextLine();
                        Course newCourse = em.find(Course.class, newCourseId);//Get course object from database for check if exists
                        em.getTransaction().begin();//Begin transaction
                        recipeToUpdate.setName(newName);//Update recipe information
                        recipeToUpdate.setDifficulty(newDifficulty);
                        if (newCourse != null) {//If course exists
                            recipeToUpdate.setCourse(newCourse);//Update course for this recipe
                        } else {
                            System.out.println("Course not found");
                        }
                        em.getTransaction().commit();//Commit transaction
                        System.out.println("Recipe updated");
                    } else {
                        System.out.println("Recipe not found");
                    }
                    break;
                case 4:
                    System.out.print("Enter recipe ID to delete: ");//Get recipe ID to delete
                    int idDelete = sc.nextInt();
                    sc.nextLine();
                    Recipe recipeToDelete = em.find(Recipe.class, idDelete);//Get recipe object from database for check if exists
                    if (recipeToDelete != null) {//If recipe exists
                        em.getTransaction().begin();//Begin transaction
                        em.remove(recipeToDelete);//Delete recipe object
                        em.getTransaction().commit();//Commit transaction
                        System.out.println("Recipe deleted");
                    } else {
                        System.out.println("Recipe not found");
                    }
                    break;
                case 5://Back to main menu
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void manageStudents(EntityManager em) {//Method to manage students
        while (true) {
            System.out.println("--Manage Students--");//Menu options for students
            System.out.println("1 - Add student");
            System.out.println("2 - View students");
            System.out.println("3 - Update student");
            System.out.println("4 - Delete student");
            System.out.println("5 - Back");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {//Switch case for menu options
                case 1://Add student
                    System.out.print("Enter student name: ");//Get student information
                    String studentName = sc.nextLine();
                    System.out.print("Enter course ID for this student: ");
                    int courseId = sc.nextInt();
                    sc.nextLine();
                    Course course = em.find(Course.class, courseId);//Get course object from database for check if exists
                    if (course != null) {//If course exists
                        em.getTransaction().begin();//Begin transaction
                        Student student = new Student(studentName);//Create new student object for persist into database
                        student.setCourse(course);//Set course for this student
                        em.persist(student);//Persist student object
                        em.getTransaction().commit();//Commit transaction
                        System.out.println("Student added");
                    } else {
                        System.out.println("Course not found");
                    }
                    break;
                case 2://View students
                    List<Student> students = em.createQuery("SELECT s FROM Student s", Student.class).getResultList();//Get all students from database
                    if (students.isEmpty()) {//If no students found
                        System.out.println("Students not found");
                    }
                    for (Student s : students) {//Print student information
                        System.out.println("ID: " + s.getId() + ", Name: " + s.getName() +", Course: " + s.getCourse().getName());
                    }
                    break;
                case 3://Update student
                    System.out.print("Enter student ID to update: ");//Get student ID to update
                    int idUpdate = sc.nextInt();
                    sc.nextLine();
                    Student studentToUpdate = em.find(Student.class, idUpdate);//Get student object from database for check if exists
                    if (studentToUpdate != null) {//If student exists
                        System.out.print("Enter new name: ");//Get new information
                        String newName = sc.nextLine();
                        System.out.print("Enter new course ID: ");
                        int newCourseId = sc.nextInt();
                        sc.nextLine();
                        Course newCourse = em.find(Course.class, newCourseId);//Get course object from database for check if exists
                        em.getTransaction().begin();//Begin transaction
                        studentToUpdate.setName(newName);//Update student information
                        if (newCourse != null) {//If course exists
                            studentToUpdate.setCourse(newCourse);//Update course for this student
                        } else {
                            System.out.println("Course not found");
                        }
                        em.getTransaction().commit();//Commit transaction
                        System.out.println("Student updated");
                    } else {
                        System.out.println("Student not found");
                    }
                    break;
                case 4://Delete student
                    System.out.print("Enter student ID to delete: ");//Get student ID to delete
                    int idDelete = sc.nextInt();
                    sc.nextLine();
                    Student studentToDelete = em.find(Student.class, idDelete);//Get student object from database for check if exists
                    if (studentToDelete != null) {//If student exists
                        em.getTransaction().begin();//Begin transaction
                        em.remove(studentToDelete);//Delete student object
                        em.getTransaction().commit();//Commit transaction
                        System.out.println("Student deleted");
                    } else {
                        System.out.println("Student not found");
                    }
                    break;
                case 5://Back to main menu
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}