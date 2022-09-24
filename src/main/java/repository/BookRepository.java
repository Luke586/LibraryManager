package repository;

import dto.Book;
import dto.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;


public class BookRepository {

    private static final SessionFactory factory = SessionManager.getSessionFactory();

    public Book createBook(Book book) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(book);
            transaction.commit();

        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(exception.getClass() + ": " + exception.getMessage());
        }
        return book;
    }

    public List<Book> findAllBooks() {
        Session session = factory.openSession();
        Transaction transaction = null;
        List<Book> books = null;
        try {
            transaction = session.beginTransaction();
            books = session.createQuery("From Book", Book.class).list();
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(exception.getClass() + ": " + exception.getMessage());
        } finally {
            session.close();
        }
        return books;
    }
    public void viewBooks() {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            List<Book> books = session.createQuery("From Book", Book.class).list();
            for (Book book : books) {
                System.out.println("Book ID: " + book.getId());
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
    }

    public void viewFoundBooks(List<Book> bookList) {
        for (Book book : bookList) {
            System.out.println("Book ID: " + book.getId());
            System.out.println("Book title: " + book.getTitle());
            System.out.println("Book author: " + book.getAuthor());
            System.out.println("Book genre: " + book.getGenre());
            System.out.println("Copies of book: " + book.getCopiesOfBook());
            System.out.println("=====================================");
        }
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

    public List<Book> searchBookByTitle(String title) {
        Transaction transaction = null;
        List<Book> bookList = new ArrayList<>();
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Query<Book> bookQuery = session.createQuery("From Book Where title like :bookTitle", Book.class);
            bookQuery.setParameter("bookTitle", '%' + title + '%');
            bookList = bookQuery.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return bookList;
    }

    public Book deleteBook(Book book) {
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
      return book;
    }
    public Book returnBook(Book book) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
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

    public Book borrowBook(Book book) {
        Transaction transaction = null;

        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();

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

    public List<Book> findCustomersBorrowedBooks(Customer customer) {
        Transaction transaction = null;
        List<Book> bookList = new ArrayList<>();
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Query<Book> bookQuery = session.createNativeQuery("", Book.class);
            bookList = bookQuery.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return bookList;
    }

    public void viewFoundCustomerBorrowedBooks(List<Book> customerList) {
        for (Book book : customerList) {
            System.out.println("Book ID: " + book.getId());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author:: " + book.getAuthor());
            System.out.println("=====================================");
        }
    }
}

