package org.example;

import org.hibernate.*;//Import libraries
import org.hibernate.cfg.*;
import java.io.*;
import java.util.*;

public class HibernateUtil {//Hibernate utility class

    private static SessionFactory sessionFactory;//SessionFactory variable

    public static void buildSessionFactory() {//Method to build the SessionFactory
        try {
            BufferedReader br = new BufferedReader(new FileReader("bbdd_connection.txt"));//Read database connection data from file
            String url = br.readLine();//Read values
            String user = br.readLine();
            String pass = br.readLine();
            Properties props = new Properties();//Set Hibernate properties
            props.put("hibernate.connection.url", url);//Database put properties
            props.put("hibernate.connection.username", user);
            props.put("hibernate.connection.password", pass);
            props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
            props.put("hibernate.hbm2ddl.auto", "update");
            props.put("hibernate.show_sql", "true");
            Configuration cfg = new Configuration();//Create Configuration object
            cfg.addProperties(props);//Add properties to configuration
            cfg.addAnnotatedClass(org.example.Director.class);
            cfg.addAnnotatedClass(org.example.Movie.class);
            sessionFactory = cfg.buildSessionFactory();//Build SessionFactory
            br.close();//Close BufferedReader
        } catch (Exception e) {//Handle exceptions
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {//Method to get the SessionFactory
        return sessionFactory;
    }
}
