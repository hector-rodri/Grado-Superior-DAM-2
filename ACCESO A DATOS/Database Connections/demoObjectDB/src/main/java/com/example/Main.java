package com.example;

import javax.persistence.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb_example");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Empresa cide = new Empresa("CIDE Coop");
            Empresa acme = new Empresa("ACME Corp");

            Persona juan = new Persona("Juan", 30);
            Persona maria = new Persona("Maria", 25);
            Persona Paco = new Persona("Paco", 45);

            cide.addEmpleado(juan);
            cide.addEmpleado(maria);
            acme.addEmpleado(Paco);

            em.persist(cide);
            em.persist(acme);

            em.getTransaction().commit();

            List<Persona> personas = em.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();
            System.out.println("Personas guardadas:");
            personas.forEach(System.out::println);

        } finally {
            em.close();
            emf.close();
        }
    }
}