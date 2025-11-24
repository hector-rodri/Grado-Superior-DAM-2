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
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("Exiting the system...");
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
                    if (chef.getId() != 0) {
                        System.out.println("Chef added");
                    }else {
                        System.out.println("Error adding chef");
                    }
                    break;
                case 2:
                    List<Chef> chefs = em.createQuery("SELECT c FROM Chef c", Chef.class).getResultList();
                    for (Chef c : chefs) {
                        System.out.println(c.getId() + " - " + c.getName() + " (" + c.getSpecialty() + ")");
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
                        System.out.println("Chef updated successfully!");
                    } else {
                        System.out.println("Chef not found.");
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
                        System.out.println("Chef deleted successfully!");
                    } else {
                        System.out.println("Chef not found.");
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