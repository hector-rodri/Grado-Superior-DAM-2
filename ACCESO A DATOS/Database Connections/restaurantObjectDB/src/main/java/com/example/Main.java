package com.example;

import javax.persistence.*;

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
}