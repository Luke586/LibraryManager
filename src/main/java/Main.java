import controller.MenuController;
import dto.Book;
import dto.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {

//       SessionFactory sessionFactory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(Book.class)
//                .addAnnotatedClass(Customer.class)
//                .buildSessionFactory();
//
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        ArrayList<Book> allBooks = (ArrayList<Book>) session.createQuery("From books", Book.class).getResultList();
//
//        System.out.println(allBooks);

        MenuController menuController = new MenuController();

        menuController.start();





    }
}

