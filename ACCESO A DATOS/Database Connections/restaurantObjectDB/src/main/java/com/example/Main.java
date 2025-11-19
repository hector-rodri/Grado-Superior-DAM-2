package com.example;

import javax.persistence.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb_restaurant");
        EntityManager em = emf.createEntityManager();

        // while (true) {}
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
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                System.out.println("Exiting the system...");
                return;
            default:
                System.out.println("Invalid option");
        }

        try {
            em.getTransaction().begin();

            Chef chef1 = new Chef("Hector Rodriguez", "Fish Dishes");
            Chef chef2 = new Chef("Jaume Salas", "Meat Dishes");
            Course course1 = new Course("Seafood", 2);
            Course course2 = new Course("Meat", 1);
            Recipe recipe1 = new Recipe("Salmon", "Medium");
            Recipe recipe2 = new Recipe("Beef Steak", "Hard");
            Student student1 = new Student("Alberto");
            Student student2 = new Student("Elena");

            chef1.addCourse(course1);
            chef2.addCourse(course2);
            course1.addRecipe(recipe1);
            course2.addRecipe(recipe2);
            course1.addStudent(student1);
            course2.addStudent(student2);

            em.persist(chef1);
            em.persist(chef2);

            em.getTransaction().commit();

        } finally {
            em.close();
            emf.close();
        }
    }

    private static void manageChefs() {
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

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option");

            }
        }
    }
}