package com.example;

import org.hibernate.*;

public class CourseCRUD {

    public void create(Course course) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.persist(course);
        tx.commit();
        session.close();
    }

    public Course read(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Course course = session.get(Course.class, id);
        session.close();
        return course;
    }

    public void update(Course course) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.merge(course);
        tx.commit();
        session.close();
    }

    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Course course = session.get(Course.class, id);
        if (course != null) session.remove(course);
        tx.commit();
        session.close();
    }
}
