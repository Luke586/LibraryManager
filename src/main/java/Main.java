import controller.MenuController;
import dto.Book;
import dto.Customer;
import repository.BookRepository;
import repository.CustomerRepository;



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
//        MenuController menuController = new MenuController();
//
//        menuController.start();
//
        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer = customerRepository.findCustomerById(2L);
        System.out.println(customer.getBooks());
        BookRepository bookRepository = new BookRepository();
        Book book = bookRepository.findBookById(1L);

//        book.getCustomer().forEach(customer -> System.out.println(customer.getFirstName()));
        book.getCustomer().forEach(System.out::println);
        System.out.println("12312423kj54k23j5jh324jtkhrjhagjhahjgfsdhjgh'akgkdgjgkajgas");

//        List<Book> allBook = bookRepository.findAllBooks();
//        List<Book> booksCustomerHasBorrowed = allBook.stream().flatMap(book1 -> book1.getCustomer().stream().filter(customer1 -> customer1.getId() == customer.getId()));








    }
}

