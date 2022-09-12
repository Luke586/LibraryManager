package repository;

import dto.Book;
import dto.Customer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionManager {

    private static final SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Book .class)
                .addAnnotatedClass(Customer .class)
                .buildSessionFactory();


    public static SessionFactory getSessionFactory(){

        return sessionFactory;
    }
}
