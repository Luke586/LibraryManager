
import dto.Book;
import dto.Customer;
import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import repository.BookRepository;
import repository.CustomerRepository;
import repository.SessionManager;

import java.util.List;


@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class TestController {
    private static SessionFactory sessionFactory;
    private static CustomerRepository customerRepository;
    private static BookRepository bookRepository;
    private static Customer customer;
    private static Book book;
    private static Session session;

    @BeforeAll
    public static void startUp(){
        sessionFactory = SessionManager.getSessionFactory();
        customerRepository = new CustomerRepository();
        customer = Customer.builder().id(1L).firstName("John").surname("Doe").build();
        bookRepository = new BookRepository();
        book = Book.builder().id(2L).title("Harry Potter").author("J.K. Rowling").genre("Fantasy").copiesOfBook(5L).build();
        System.out.println("SessionFactory created!");
    }
    @AfterAll
    public static void end(){
        if (sessionFactory != null){
            sessionFactory.close();
            System.out.println("SessionFactory closed!");
        }
    }
    @BeforeEach
    public void openSession(){
        session = sessionFactory.openSession();
        System.out.println("Session created!");
        System.out.println(book.getId());
        System.out.println(customer.getId());
    }
    @AfterEach
    public void closeSession() {
        if (session != null) {
            session.close();
            System.out.println("Session closed!");
        }
    }
    @Test
    @Order(1)
    public void testCreateCustomer(){
        customer = customerRepository.createCustomer(customer);
        Assertions.assertThat(customer.getId()).isGreaterThan(0);
    }
    @Test
    @Order(2)
    public void testRemoveCustomer(){
        Customer findCustomer = customerRepository.findCustomerById(1L);
        Assertions.assertThat(findCustomer).isNotNull();
        customerRepository.deleteCustomer(findCustomer);
        Customer removeCustomer = customerRepository.findCustomerById(1L);
        Assertions.assertThat(removeCustomer).isNull();
    }

    @Test
    @Order(3)
    public void testCreateBook(){
        book = bookRepository.createBook(book);
        Assertions.assertThat(book.getId()).isGreaterThan(0);
    }
    @Test
    @Order(4)
    public void testRemoveBook(){

        Book findBook = bookRepository.findBookById(book.getId());
        Assertions.assertThat(findBook).isNotNull();
        bookRepository.deleteBook(book);
        Book removeBook = bookRepository.findBookById(book.getId());
        Assertions.assertThat(removeBook).isNull();
    }
    @Test
    @Order(5)
    public void testFindAllBook(){
        List<Book> bookList = bookRepository.findAllBooks();
        System.out.println("Amount of books in Library = " +bookList.size());
        Assertions.assertThat(bookList).isNotEmpty();
    }
    @Test
    @Order(6)
    public void testUpdateQuantityOfBooks(){
        Book update = bookRepository.updateBook(book.getCopiesOfBook(), 6L);
        Assertions.assertThat(update.getCopiesOfBook()).isEqualTo(5L);
    }

    @Test
    @Order(7)
    public void testBorrowABook(){
        Book borrow = bookRepository.borrowBook(book);
        Assertions.assertThat(borrow).isNotNull();
    }

    @Test
    @Order(8)
    public void testCustomerBorrowedBookList(){
        List<Book> bookList;
        Book borrow = bookRepository.borrowBook(book);
        Assertions.assertThat(borrow).isNotNull();
        Customer customer = customerRepository.findCustomerById(9L);
        bookList = customer.getBooks();
        bookRepository.viewFoundCustomerBorrowedBooks(bookList);
        Assertions.assertThat(bookList).isNotEmpty();
    }




























































}
