package com.example;

import javax.persistence.*;
import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb_restaurant");
        EntityManager em = emf.createEntityManager();

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
                    manageChefs(em);
                    break;
                case 2:
                    manageCourses(em);
                    break;
                case 3:
                    manageStudents(em);
                    break;
                case 4:
                    manageRecipes(em);
                    break;
                case 5:
                    em.close();
                    emf.close();
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void manageChefs(EntityManager em) {
        while (true) {
            System.out.println("--Manage Chefs--");
            System.out.println("1 - Add chef");
            System.out.println("2 - View chefs");
            System.out.println("3 - Update chef");
            System.out.println("4 - Delete chef");
            System.out.println("5 - Back");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter chef name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter specialty: ");
                    String specialty = sc.nextLine();
                    em.getTransaction().begin();
                    Chef chef = new Chef(name, specialty);
                    em.persist(chef);
                    em.getTransaction().commit();
                    System.out.println("Chef added");
                    break;
                case 2:
                    List<Chef> chefs = em.createQuery("SELECT c FROM Chef c", Chef.class).getResultList();
                    if (chefs.isEmpty()) {
                        System.out.println("Chefs not found");
                    }
                    for (Chef c : chefs) {
                        System.out.println(
                                "ID: " + c.getId() + ", Name: " + c.getName() + ", Speciality: " + c.getSpecialty());
                    }
                    break;
                case 3:
                    System.out.print("Enter chef ID to update: ");
                    long idUpdate = sc.nextLong();
                    sc.nextLine();
                    Chef chefToUpdate = em.find(Chef.class, idUpdate);
                    if (chefToUpdate != null) {
                        System.out.print("Enter new name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter new specialty: ");
                        String newSpecialty = sc.nextLine();
                        em.getTransaction().begin();
                        chefToUpdate.setName(newName);
                        chefToUpdate.setSpecialty(newSpecialty);
                        em.getTransaction().commit();
                        System.out.println("Chef updated");
                    } else {
                        System.out.println("Chef not found");
                    }
                    break;
                case 4:
                    System.out.print("Enter chef ID to delete: ");
                    long idDelete = sc.nextLong();
                    sc.nextLine();
                    Chef chefToDelete = em.find(Chef.class, idDelete);
                    if (chefToDelete != null) {
                        em.getTransaction().begin();
                        em.remove(chefToDelete);
                        em.getTransaction().commit();
                        System.out.println("Chef deleted");
                    } else {
                        System.out.println("Chef not found");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void manageCourses(EntityManager em) {
        while (true) {
            System.out.println("--Manage Courses--");
            System.out.println("1 - Add course");
            System.out.println("2 - View courses");
            System.out.println("3 - Update course");
            System.out.println("4 - Delete course");
            System.out.println("5 - Back");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter course name: ");
                    String courseName = sc.nextLine();
                    System.out.print("Enter duration: ");
                    int duration = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter chef ID for this course: ");
                    long chefId = sc.nextLong();
                    sc.nextLine();
                    Chef chef = em.find(Chef.class, chefId);
                    if (chef != null) {
                        em.getTransaction().begin();
                        Course course = new Course(courseName, duration);
                        course.setChef(chef);
                        em.persist(course);
                        em.getTransaction().commit();
                        System.out.println("Course added");
                    } else {
                        System.out.println("Chef not found");
                    }
                    break;
                case 2:
                    List<Course> courses = em.createQuery("SELECT c FROM Course c", Course.class).getResultList();
                    if (courses.isEmpty()) {
                        System.out.println("Courses not found");
                    }
                    for (Course c : courses) {
                        System.out.println(
                                "ID: " + c.getId() + ", Name: " + c.getName() + ", Duration: " + c.getDuration() +
                                        ", Chef: " + (c.getChef() != null ? c.getChef().getName() : "None"));
                    }
                    break;
                case 3:
                    System.out.print("Enter course ID to update: ");
                    int idUpdate = sc.nextInt();
                    sc.nextLine();
                    Course courseToUpdate = em.find(Course.class, idUpdate);
                    if (courseToUpdate != null) {
                        System.out.print("Enter new name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter new duration: ");
                        int newDuration = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter new chef ID: ");
                        long newChefId = sc.nextLong();
                        sc.nextLine();
                        Chef newChef = em.find(Chef.class, newChefId);
                        em.getTransaction().begin();
                        courseToUpdate.setName(newName);
                        courseToUpdate.setDuration(newDuration);
                        if (newChef != null) {
                            courseToUpdate.setChef(newChef);
                        }
                        em.getTransaction().commit();
                        System.out.println("Course updated");
                    } else {
                        System.out.println("Course not found");
                    }
                    break;
                case 4:
                    System.out.print("Enter course ID to delete: ");
                    int idDelete = sc.nextInt();
                    sc.nextLine();
                    Course courseToDelete = em.find(Course.class, idDelete);
                    if (courseToDelete != null) {
                        em.getTransaction().begin();
                        em.remove(courseToDelete);
                        em.getTransaction().commit();
                        System.out.println("Course deleted");
                    } else {
                        System.out.println("Course not found");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void manageRecipes(EntityManager em) {
        while (true) {
            System.out.println("--Manage Recipes--");
            System.out.println("1 - Add recipe");
            System.out.println("2 - View recipes");
            System.out.println("3 - Update recipe");
            System.out.println("4 - Delete recipe");
            System.out.println("5 - Back");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter recipe name: ");
                    String recipeName = sc.nextLine();
                    System.out.print("Enter difficulty: ");
                    String difficulty = sc.nextLine();
                    System.out.print("Enter course ID for this recipe: ");
                    int courseId = sc.nextInt();
                    sc.nextLine();
                    Course course = em.find(Course.class, courseId);
                    if (course != null) {
                        em.getTransaction().begin();
                        Recipe recipe = new Recipe(recipeName, difficulty);
                        recipe.setCourse(course);
                        em.persist(recipe);
                        em.getTransaction().commit();
                        System.out.println("Recipe added successfully!");
                    } else {
                        System.out.println("Course not found. Cannot assign recipe.");
                    }
                    break;
                case 2:
                    List<Recipe> recipes = em.createQuery("SELECT r FROM Recipe r", Recipe.class).getResultList();
                    if (recipes.isEmpty()) {
                        System.out.println("Recipes not found");
                    }
                    for (Recipe r : recipes) {
                        System.out.println("ID: " + r.getId() + ", Name: " + r.getName() +
                                ", Difficulty: " + r.getDifficulty() +
                                ", Course: " + (r.getCourse() != null ? r.getCourse().getName() : "None"));
                    }
                    break;
                case 3:
                    System.out.print("Enter recipe ID to update: ");
                    int idUpdate = sc.nextInt();
                    sc.nextLine();
                    Recipe recipeToUpdate = em.find(Recipe.class, idUpdate);
                    if (recipeToUpdate != null) {
                        System.out.print("Enter new name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter new difficulty: ");
                        String newDifficulty = sc.nextLine();
                        System.out.print("Enter new course ID: ");
                        int newCourseId = sc.nextInt();
                        sc.nextLine();
                        Course newCourse = em.find(Course.class, newCourseId);
                        em.getTransaction().begin();
                        recipeToUpdate.setName(newName);
                        recipeToUpdate.setDifficulty(newDifficulty);
                        if (newCourse != null) {
                            recipeToUpdate.setCourse(newCourse);
                        }
                        em.getTransaction().commit();
                        System.out.println("Recipe updated successfully!");
                    } else {
                        System.out.println("Recipe not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter recipe ID to delete: ");
                    int idDelete = sc.nextInt();
                    sc.nextLine();
                    Recipe recipeToDelete = em.find(Recipe.class, idDelete);
                    if (recipeToDelete != null) {
                        em.getTransaction().begin();
                        em.remove(recipeToDelete);
                        em.getTransaction().commit();
                        System.out.println("Recipe deleted successfully!");
                    } else {
                        System.out.println("Recipe not found.");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void manageStudents(EntityManager em) {
        while (true) {
            System.out.println("--Manage Students--");
            System.out.println("1 - Add student");
            System.out.println("2 - View students");
            System.out.println("3 - Update student");
            System.out.println("4 - Delete student");
            System.out.println("5 - Back");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String studentName = sc.nextLine();
                    System.out.print("Enter course ID for this student: ");
                    int courseId = sc.nextInt();
                    sc.nextLine();
                    Course course = em.find(Course.class, courseId);
                    if (course != null) {
                        em.getTransaction().begin();
                        Student student = new Student(studentName);
                        student.setCourse(course);
                        em.persist(student);
                        em.getTransaction().commit();
                        System.out.println("Student added successfully!");
                    } else {
                        System.out.println("Course not found. Cannot assign student.");
                    }
                    break;
                case 2:
                    List<Student> students = em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
                    if (students.isEmpty()) {
                        System.out.println("Students not found");
                    }
                    for (Student s : students) {
                        System.out.println("ID: " + s.getId() + ", Name: " + s.getName() +
                                ", Course: " + (s.getCourse() != null ? s.getCourse().getName() : "None"));
                    }
                    break;
                case 3:
                    System.out.print("Enter student ID to update: ");
                    int idUpdate = sc.nextInt();
                    sc.nextLine();
                    Student studentToUpdate = em.find(Student.class, idUpdate);
                    if (studentToUpdate != null) {
                        System.out.print("Enter new name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter new course ID: ");
                        int newCourseId = sc.nextInt();
                        sc.nextLine();
                        Course newCourse = em.find(Course.class, newCourseId);
                        em.getTransaction().begin();
                        studentToUpdate.setName(newName);
                        if (newCourse != null) {
                            studentToUpdate.setCourse(newCourse);
                        }
                        em.getTransaction().commit();
                        System.out.println("Student updated successfully!");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter student ID to delete: ");
                    int idDelete = sc.nextInt();
                    sc.nextLine();
                    Student studentToDelete = em.find(Student.class, idDelete);
                    if (studentToDelete != null) {
                        em.getTransaction().begin();
                        em.remove(studentToDelete);
                        em.getTransaction().commit();
                        System.out.println("Student deleted successfully!");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}