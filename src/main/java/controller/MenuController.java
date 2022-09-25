package controller;

import org.hibernate.SessionFactory;
import javax.swing.*;
import java.util.Scanner;

public class MenuController {
    SessionFactory sessionFactory;
    CustomerPortal customerPortal = new CustomerPortal();
    BookController bookController = new BookController();
    public void start() {
        this.handleUserChoice();
    }
    private void handleUserChoice() {

        String userChoice = JOptionPane.showInputDialog("" +
                "Welcome to the Library" +
                "\nPlease choose an option: " +
                "\n 1. Add a book" +
                "\n 2. View all books" +
                "\n 3. Customer portal" +
                "\n 4. Remove a book" +
                "\n 5. Update a book" +
                "\n 6. Exit" +
                "");
        System.out.println(userChoice);
        switch (userChoice) {
            case "1" -> this.bookController.createBook();
            case "2" -> this.bookController.viewBooks();
            case "3" -> this.customerPortal.customerPortal();
            case "4" -> this.bookController.removeBookById();
            case "5" -> this.bookController.updateBook();
            case "6" -> System.exit(0);
            default -> System.out.println("Choose an option from the list!");
        }
        start();
    }
}
