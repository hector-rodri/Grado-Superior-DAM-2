package com.example;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import java.io.*;
import java.util.*;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            Properties props = new Properties();
            BufferedReader br = new BufferedReader(new FileReader("bbdd_connection.txt"));
            props.load(br);

            Configuration cfg = new Configuration().configure();

            cfg.setProperty("hibernate.connection.url", props.getProperty("url"));
            cfg.setProperty("hibernate.connection.username", props.getProperty("user"));
            cfg.setProperty("hibernate.connection.password", props.getProperty("password"));

            sessionFactory = cfg.buildSessionFactory();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}