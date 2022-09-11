package controller;


import dto.Book;
import org.hibernate.SessionFactory;
import repository.BookRepository;

import java.util.Scanner;

public class BookController {


    static SessionFactory factory;

    private final Scanner scanner = new Scanner(System.in);

    BookRepository bookRepository = new BookRepository(factory);


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
        System.out.println("Enter Book id to remove: ");
        Long id = Long.parseLong(scanner.nextLine());
        Book book = bookRepository.findBookById(id);
        bookRepository.deleteBook(book);
    }

    public void viewBooks() {
        bookRepository.viewBooks();
    }

    public void updateBook(){
        System.out.println("Enter Book id to update: ");
        Long id = Long.parseLong(scanner.nextLine());
        bookRepository.findBookById(id);
        System.out.println("How many copies do you have? ");
        Long amount = scanner.nextLong();
        bookRepository.updateBook(amount, id);
    }

    public void borrowBook(){
        bookRepository.viewBooks();
        System.out.println("What book would you like to borrow? ");
        bookRepository.borrowBook(scanner.nextLine());

    }






}
