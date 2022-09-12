package controller;


import dto.Book;
import dto.Customer;
import org.hibernate.SessionFactory;
import repository.BookRepository;
import repository.CustomerRepository;

import java.util.List;
import java.util.Scanner;

public class BookController {
    static SessionFactory factory;
    private final Scanner scanner = new Scanner(System.in);
    BookRepository bookRepository = new BookRepository();
    CustomerRepository customerRepository = new CustomerRepository();
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
        Book book = null;
        List<Book> bookList;
        Customer customer = null;
        bookRepository.viewBooks();
        System.out.println("Please enter the title of book you want to borrow: ");
        String borrow = scanner.nextLine();
        bookList = bookRepository.searchBookByTitle(borrow);
        bookRepository.viewFoundBooks(bookList);
        System.out.println("Please enter Id of the book you want to borrow: ");
        Long bookId = Long.valueOf(scanner.nextLine());
        book = bookRepository.findBookById(bookId);
        customerRepository.displayCustomers();
        System.out.println("Please enter your customer ID");
        Long customerId = Long.valueOf(scanner.nextLine());
        customer = customerRepository.findCustomerById(customerId);
        if(book != null && customer != null){
            if(book.getCopiesOfBook()-1 < 0){
                System.out.println("Sorry no copies left");
                return;
            }
            book.getCustomer().add(customer);
            book.setCopiesOfBook(book.getCopiesOfBook()-1);
            bookRepository.borrowBook(book);
        }
      }
    }





