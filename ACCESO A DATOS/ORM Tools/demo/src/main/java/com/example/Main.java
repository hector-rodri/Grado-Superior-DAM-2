package com.example;


import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Empleado emp = new Empleado();
        emp.setNombre("Juan");
        emp.setPuesto("IT");

        session.persist(emp);   // INSERT

        tx.commit();
        session.close();

        System.out.println("Empleado guardado");
    }
}
