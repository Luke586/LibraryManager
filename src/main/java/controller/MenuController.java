package controller;

import dto.Book;
import dto.Customer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import repository.BookRepository;
import repository.CustomerRepository;

import java.util.Scanner;

public class MenuController {

    SessionFactory sessionFactory;

    private final Scanner scanner = new Scanner(System.in);
    CustomerPortal customerPortal = new CustomerPortal();

    BookController bookController = new BookController();

    public void start() {
        this.showOption();
        this.handleUserChoice();
    }
    public void showOption() {
        System.out.println("" +
                "Welcome to the Library" +
                "\n 1. Add a book" +
                "\n 2. View all books" +
                "\n 3. Customer portal" +
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
                this.customerPortal.customerPortal();
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
