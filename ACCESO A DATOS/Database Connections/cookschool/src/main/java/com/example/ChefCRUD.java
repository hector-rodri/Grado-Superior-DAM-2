package com.example;

import org.hibernate.*;

public class ChefCRUD {

    public void create(Chef chef) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.persist(chef);
        tx.commit();
        session.close();
    }

    public Chef read(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Chef chef = session.get(Chef.class, id);
        session.close();
        return chef;
    }

    public void update(Chef chef) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.merge(chef);
        tx.commit();
        session.close();
    }

    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Chef chef = session.get(Chef.class, id);
        if (chef != null) session.remove(chef);
        tx.commit();
        session.close();
    }
}