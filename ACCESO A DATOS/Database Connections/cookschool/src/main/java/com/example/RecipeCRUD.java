package com.example;

import org.hibernate.*;

public class RecipeCRUD {

    public void create(Recipe recipe) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.persist(recipe);
        tx.commit();
        session.close();
    }

    public Recipe read(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Recipe recipe = session.get(Recipe.class, id);
        session.close();
        return recipe;
    }

    public void update(Recipe recipe) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.merge(recipe);
        tx.commit();
        session.close();
    }

    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Recipe recipe = session.get(Recipe.class, id);
        if (recipe != null) session.remove(recipe);
        tx.commit();
        session.close();
    }
}