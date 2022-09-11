package repository;

import dto.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;


public class BookRepository {

    static SessionFactory factory;

    public BookRepository(SessionFactory sessionFactory) {
        factory = sessionFactory;
    }



    public String createBook(Book book){
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(book);
            transaction.commit();
        }catch (Exception exception){
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(exception.getClass() + ": " + exception.getMessage());
        }finally {
            session.close();
        }

        return "Book created successfully";
    }


    public Book viewBooks() {
        Session session = factory.openSession();
        Transaction transaction = null;
        Book book = null;
        try {
            transaction = session.beginTransaction();
            List books = session.createQuery("From books").list();
            for (Object o : books) {
                book = (Book) o;
                System.out.println("Book title: " + book.getTitle());
                System.out.println("Book author: " + book.getAuthor());
                System.out.println("Book genre: " + book.getGenre());
                System.out.println("Copies of book: " + book.getCopiesOfBook());
                System.out.println("=====================================");
            }
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(exception.getClass() + ": " + exception.getMessage());
        } finally {
            session.close();
        }
        return book;
    }

    public Book updateBook(Long copiesOfBook, Long id) {
        Transaction transaction = null;
        Book book = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            book = session.find(Book.class, id);
            book.setCopiesOfBook(copiesOfBook);
            session.merge(book);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return book;
    }


    public Book findBookById(Long id) {
        Transaction transaction = null;
        Book book = null;

        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();

            book = session.find(Book.class, id);
            System.out.println(book);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return book;
    }

    public String deleteBook(Book book) {
        Transaction transaction = null;

        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();

            session.remove(book);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return "Book removed successfully!";
    }

    public Book borrowBook(String bookTitle){
        Transaction transaction = null;
        Book book = null;

        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            book = session.find(Book.class, bookTitle);
            session.merge(book);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return book;
    }
}

