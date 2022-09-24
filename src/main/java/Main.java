import controller.MenuController;
import dto.Book;
import dto.Customer;
import repository.BookRepository;
import repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;


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
//
        MenuController menuController = new MenuController();

        menuController.start();

//        CustomerRepository customerRepository = new CustomerRepository();
//        Customer customer = customerRepository.findCustomerById(4L);
//        System.out.println(customer.getBooks());
//        System.out.println("===========================================");
//        BookRepository bookRepository = new BookRepository();
//        Book book = bookRepository.findBookById(1L);
//        System.out.println("===========================================");
//        System.out.println(book.getCustomer());

//        System.out.println("==========Remove============");
////        customer.getBooks().remove(book);
//        customerRepository.findCustomerById(4L).getBooks().remove(book);
//
//        System.out.println("New!!");
//
//        System.out.println(customer.getBooks());

//        List <Book> books = new ArrayList<>();
//
//        System.out.println("Next");
//
//        Book book = bookRepository.findBookById(3L);
//        System.out.println(book.getCustomer());
//
//        Customer customer = customerRepository.findCustomerById(5L);

////        book.getCustomer().remove(customer);
//        System.out.println("ljsdlhgfsdlnf");
//        System.out.println(book);
//        System.out.println(customer);
//        System.out.println("sdlifjdslfdlk");
//
//        book.getCustomer().clear();
//
//        System.out.println("updated");
//
//        System.out.println(book.getCustomer());




//
//        book.getCustomer().forEach(customer -> System.out.println(customer.getFirstName()));
//        book.getCustomer().forEach(System.out::println);
//        System.out.println("====================================================");
//
//        List<Book> allBook = bookRepository.findAllBooks();
//        System.out.println(allBook);
//        List<Book> booksCustomerHasBorrowed = allBook.stream().flatMap(book1 -> book1.getCustomer().stream().filter(customer1 -> customer1.getId() == customer.getId()));








    }
}

