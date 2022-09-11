package repository;

import dto.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



public class BookRepository {

    static SessionFactory factory;

    public BookRepository(SessionFactory sessionFactory) {
        factory = sessionFactory;
    }

    //private final List<Book> books = new ArrayList<>();

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





}
