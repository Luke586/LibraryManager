package controller;

import dto.Book;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import repository.BookRepository;

import java.util.Scanner;

public class MenuController {

    public void start() {
        this.showOption();
        this.handleUserChoice();

    }


    SessionFactory sessionFactory;

    private final Scanner scanner = new Scanner(System.in);
    private final BookRepository bookRepository;

    public MenuController() {

        this.sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Book.class)
                .buildSessionFactory();
        this.bookRepository = new BookRepository(this.sessionFactory);
    }


    public void createBook() {
        Book book = new Book();

        System.out.println("Please enter title of book");
        String title = scanner.nextLine();
        book.setTitle(title);
        System.out.println("Please enter author of book");
        String author = scanner.nextLine();
        book.setAuthor(author);
        System.out.println("Please enter genre of book");
        String genre = scanner.nextLine();
        book.setGenre(genre);
        System.out.println("Please enter how many copies of the book");
        Long copies = Long.valueOf(scanner.nextLine());
        book.setCopiesOfBook(copies);
        bookRepository.createBook(book);
    }

    public void removeBookById() {
        System.out.println("Enter Book id: ");
        Long id = Long.parseLong(scanner.nextLine());
        Book book = bookRepository.findBookById(id);

        bookRepository.deleteBook(book);
    }

    private void handleUserChoice() {
        System.out.println("Choose an option: ");
        String userChoice = scanner.nextLine();

        switch (userChoice) {
            case "1":
                this.createBook();
                break;
            case "4":
                this.removeBookById();
                break;
            case "6":
                System.exit(0);
                break;
            default:
                System.out.println("Choose an option from the list!");
        }
        start();
    }


    public void showOption() {
        System.out.println("" +
                "Welcome to the Library" +
                "\n 1. Create a book" +
                "\n 2. View all books" +
                "\n 3. View todo item" +
                "\n 4. Remove a book" +
                "\n 5. Update a book" +
                "\n 6. Exit" +
                "");


    }
}
