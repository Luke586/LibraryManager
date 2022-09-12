package repository;

import dto.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class CustomerRepository {

    private static final SessionFactory factory = SessionManager.getSessionFactory();

    public String createCustomer(Customer customer) {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(customer);

            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(exception.getClass() + ": " + exception.getMessage());
        } finally {
            session.close();
        }
        return "Book created successfully";
    }

    public String deleteCustomer(Customer customer) {
        Transaction transaction = null;

        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();

            session.remove(customer);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return "Book removed successfully!";
    }

    public Customer findCustomerById(Long id) {
        Transaction transaction = null;
        Customer customer = null;

        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();

            customer = session.find(Customer.class, id);
            System.out.println(customer);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return customer;
    }

    public void displayCustomers() {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            List<Customer> customers = session.createQuery("From customers",Customer.class).list();
            for (Customer o : customers) {
                System.out.println("Customer ID: " + o.getId());
                System.out.println("Customer name : " + o.getFirstName());
                System.out.println("Customer surname: " + o.getSurname());
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
}
