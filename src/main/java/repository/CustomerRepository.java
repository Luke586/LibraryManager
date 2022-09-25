package repository;

import dto.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    private static final SessionFactory factory = SessionManager.getSessionFactory();

    public Customer createCustomer(Customer customer) {

        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(customer);
            transaction.commit();

        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(exception.getClass() + ": " + exception.getMessage());
        }
        return customer;
    }
    public void deleteCustomer(Customer customer) {
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
    public List<Customer> searchCustomerByName(String name) {
        Transaction transaction = null;
        List<Customer> customerList = new ArrayList<>();
        try (Session session = factory.openSession()){
            transaction = session.beginTransaction();
            Query<Customer> customerQuery = session.createQuery("From Customer Where surname like :customerName",Customer.class);
            customerQuery.setParameter("customerName",'%' + name + '%');
            customerList = customerQuery.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return customerList;
    }
    public List<Customer> searchCustomerByAddress(String name) {
        Transaction transaction = null;
        List<Customer> customerList = new ArrayList<>();
        try (Session session = factory.openSession()){
            transaction = session.beginTransaction();
            Query<Customer> customerQuery = session.createQuery("From Customer Where address like :addressName",Customer.class);
            customerQuery.setParameter("addressName",'%' + name + '%');
            customerList = customerQuery.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return customerList;
    }
    public void viewFoundCustomerList(List<Customer> customerList) {
        for (Customer customer : customerList) {
            System.out.println("Customer ID: " + customer.getId());
            System.out.println("First name: " + customer.getFirstName());
            System.out.println("Surname: " + customer.getSurname());
            System.out.println("Address: " + customer.getAddress());
            System.out.println("=====================================");
        }
    }
    public void displayNewlyCreatedCustomer(Customer customer) {
        Transaction transaction = null;

        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.find(Customer.class, customer);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public void displayCustomers() {
        Transaction transaction = null;

        try (Session session = factory.openSession()){
            transaction = session.beginTransaction();
            List<Customer> customers = session.createQuery("From Customer",Customer.class).list();
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
        }
    }
    public void borrowBook(Customer customer){
        Transaction transaction = null;

        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(customer);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void returnBook(Customer customer){
        Transaction transaction = null;

        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(customer);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}



