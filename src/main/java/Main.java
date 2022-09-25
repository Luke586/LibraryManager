import controller.BookController;
import controller.MenuController;
import dto.Book;
import dto.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import repository.BookRepository;
import repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
////
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
//
        MenuController menuController = new MenuController();

        menuController.start();


//        book.getCustomer().forEach(customer -> System.out.println(customer.getFirstName()));
//        book.getCustomer().forEach(System.out::println);
//        System.out.println("====================================================");
//
//        List<Book> allBook = bookRepository.findAllBooks();
//        System.out.println(allBook);
//        List<Book> booksCustomerHasBorrowed = allBook.stream().flatMap(book1 -> book1.getCustomer().stream().filter(customer1 -> customer1.getId() == customer.getId()));








    }
}

