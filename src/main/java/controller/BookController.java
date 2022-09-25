package controller;


import dto.Book;
import dto.Customer;
import org.hibernate.SessionFactory;
import repository.BookRepository;
import repository.CustomerRepository;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;

public class BookController {
    static SessionFactory factory;
    BookRepository bookRepository = new BookRepository();
    CustomerRepository customerRepository = new CustomerRepository();

    public void createBook() {
        Book book = new Book();
        String title = JOptionPane.showInputDialog("Please enter title of book:");
        book.setTitle(title);
        String author = JOptionPane.showInputDialog("Please enter author of book: ");
        book.setAuthor(author);
        String genre = JOptionPane.showInputDialog("Please enter genre of book: ");
        book.setGenre(genre);
        Long copies = Long.valueOf(JOptionPane.showInputDialog("Please enter how many copies of the book: "));
        book.setCopiesOfBook(copies);
        bookRepository.createBook(book);
        System.out.println("Book created Successfully!");
    }
    public void removeBookById() {
        List<Book> bookList;
        String delete = JOptionPane.showInputDialog("Please enter the title of book you want to delete: ");
        bookList = bookRepository.searchBookByTitle(delete);
        bookRepository.viewFoundBooks(bookList);
        Long id = Long.valueOf(JOptionPane.showInputDialog("Enter Book id to remove: "));
        Book book = bookRepository.findBookById(id);
        bookRepository.deleteBook(book);
        System.out.println("\nBook deleted Successfully!");
    }

    public void viewBooks() {
        bookRepository.viewBooks();
    }
    public void updateBook() {
        List<Book> bookList;
        String borrow = JOptionPane.showInputDialog("Please enter the title of book you want to update: ");
        bookList = bookRepository.searchBookByTitle(borrow);
        bookRepository.viewFoundBooks(bookList);
        Long id = Long.valueOf(JOptionPane.showInputDialog("Enter Book id to update: "));
        bookRepository.findBookById(id);
        Long amount = Long.valueOf(JOptionPane.showInputDialog("How many copies do you have? "));
        bookRepository.updateBook(amount, id);
        System.out.println("\nBook updated successfully!");
    }
    public void borrowBook() {
        MenuController menuController = new MenuController();
        Book book = null;
        List<Book> bookList;
        Customer customer = null;
        bookRepository.viewBooks();
        String borrow = JOptionPane.showInputDialog("Please enter the title of book you want to borrow: ");
        bookList = bookRepository.searchBookByTitle(borrow);
        System.out.println("\nPlease see below list of books with the title: " +borrow);
        bookRepository.viewFoundBooks(bookList);
        Long bookId = Long.valueOf(JOptionPane.showInputDialog("Please enter Id of the book you want to borrow: "));
        book = bookRepository.findBookById(bookId);
        Long customerId = Long.valueOf(JOptionPane.showInputDialog("Please enter your customer ID"));
        customer = customerRepository.findCustomerById(customerId);
        if (book != null && customer != null) {
            if (book.getCopiesOfBook() - 1 < 0) {
                JOptionPane.showInputDialog("Sorry no copies left!");
                return;
            }
            book.setCopiesOfBook(book.getCopiesOfBook() - 1);
            bookRepository.borrowBook(book);
        }
        if (book != null && customer != null) {
//            if(customer.getBooks().stream().filter(book1 -> book1.getId().equals(book.getId()))) {
//                System.out.println("Sorry, you have already borrowed this book!");
//                return;
            customer.getBooks().add(book);
            customerRepository.borrowBook(customer);
            String userChoice = JOptionPane.showInputDialog("Thank you "+customer.getFirstName()+" "+customer.getSurname() + "." +
                    "\nPlease take good care of your borrowed book: " +
                    "\nTitle: "+book.getTitle() +
                    "\nAuthor: "+book.getAuthor()+
                    "\nGenre: "+book.getGenre() +
                    "\n1. Return to the main menu" +
                    "\n2. Exit");
            switch (userChoice) {
                case "1" -> menuController.start();
                case "2" -> System.exit(0);
            }
        }
    }
    public List<Book> findCustomerBorrowedBooks() {
        Customer customer = null;
        List<Book> bookList;
        Long customerId = Long.valueOf(JOptionPane.showInputDialog("Please enter your customer ID"));
        customer = customerRepository.findCustomerById(customerId);
        bookList = customer.getBooks();
        bookRepository.viewFoundCustomerBorrowedBooks(bookList);
        return bookList;
    }
    public void returnBook() {
        MenuController menuController = new MenuController();
        Book book = null;
        List<Book> bookList;
        Customer customer = null;
        Long customerId = Long.valueOf(JOptionPane.showInputDialog("Please enter your customer ID"));
        customer = customerRepository.findCustomerById(customerId);
        bookList = customer.getBooks();
        bookRepository.viewFoundCustomerBorrowedBooks(bookList);
        Long bookId = Long.valueOf(JOptionPane.showInputDialog("Please enter the book ID you want to return"));
        book = bookRepository.findBookById(bookId);
        if (book != null) {
            Book finalBook = book;
            customer.getBooks().removeIf(book1 -> book1.getId().equals(finalBook.getId()));
            book.setCopiesOfBook(book.getCopiesOfBook() + 1);
            bookRepository.returnBook(book);
            customerRepository.returnBook(customer);
            String userChoice = JOptionPane.showInputDialog("Hello "+customer.getFirstName()+" "+customer.getSurname() + "." +
                    "\nThank you for returning book: " +
                    "\nTitle: "+book.getTitle() +
                    "\nAuthor: "+book.getAuthor()+
                    "\nGenre: "+book.getGenre() +
                    "\n1. Return to the main menu" +
                    "\n2. Exit");
            switch (userChoice) {
                case "1" -> menuController.start();
                case "2" -> System.exit(0);
            }
        }
    }
}









