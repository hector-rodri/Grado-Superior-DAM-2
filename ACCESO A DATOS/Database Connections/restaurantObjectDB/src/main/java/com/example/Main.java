package com.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb_restaurant");
        EntityManager em = emf.createEntityManager();

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

            

        } finally {
            em.close();
            emf.close();
        }
    }
}