package controller;

import dto.Book;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import repository.BookRepository;

import java.util.Scanner;

public class MenuController {

    SessionFactory sessionFactory;

    private final Scanner scanner = new Scanner(System.in);
    private final BookRepository bookRepository;

    BookController bookController = new BookController();

    public void start() {
        this.showOption();
        this.handleUserChoice();
    }
    public MenuController() {
        this.sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Book.class)
                .buildSessionFactory();
        this.bookRepository = new BookRepository(this.sessionFactory);
    }
    public void showOption() {
        System.out.println("" +
                "Welcome to the Library" +
                "\n 1. Add a book" +
                "\n 2. View all books" +
                "\n 3. Borrow a book" +
                "\n 4. Remove a book" +
                "\n 5. Update a book" +
                "\n 6. Exit" +
                "");
    }
    private void handleUserChoice() {
        System.out.println("Choose an option: ");
        String userChoice = scanner.nextLine();

        switch (userChoice) {
            case "1":
                this.bookController.createBook();
                break;
            case "2":
                this.bookController.viewBooks();
                break;
            case "3":
                this.bookController.borrowBook();
                break;
            case "4":
                this.bookController.removeBookById();
                break;
            case "5":
                this.bookController.updateBook();
                break;
            case "6":
                System.exit(0);
                break;
            default:
                System.out.println("Choose an option from the list!");
        }
        start();
    }
}
