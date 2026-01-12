package org.example;

import org.hibernate.*;
import org.hibernate.cfg.*;
import java.io.*;
import java.util.*;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            BufferedReader br = new BufferedReader(new FileReader("bbdd_connection.txt"));
            String url = br.readLine();
            String user = br.readLine();
            String pass = br.readLine();

            Properties props = new Properties();
            props.put("hibernate.connection.url", url);
            props.put("hibernate.connection.username", user);
            props.put("hibernate.connection.password", pass);
            props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
            props.put("hibernate.hbm2ddl.auto", "update");
            props.put("hibernate.show_sql", "true");

            Configuration cfg = new Configuration();
            cfg.addProperties(props);
            cfg.addAnnotatedClass(org.example.Director.class);
            cfg.addAnnotatedClass(org.example.Movie.class);

            sessionFactory = cfg.buildSessionFactory();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
