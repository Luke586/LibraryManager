package controller;

import dto.Customer;
import repository.CustomerRepository;
import javax.swing.*;
import java.util.List;

public class CustomerPortal {
    private final BookController bookController = new BookController();
    private final CustomerRepository customerRepository = new CustomerRepository();

    public void customerPortal() {
        List<Customer> customerList;
        MenuController menuController = new MenuController();
        String account = JOptionPane.showInputDialog("Do you have an account with us? " +
                "\n 1. Yes" +
                "\n 2. No");
        if (account.equals("1")) {
            String confirmSurname = JOptionPane.showInputDialog("Please type your surname");
            customerList = customerRepository.searchCustomerByName(confirmSurname);
            customerRepository.viewFoundCustomerList(customerList);
            System.out.println("Please take note of your Customer ID!");
            String userChoice = JOptionPane.showInputDialog("Would you like to: " +
                    "\n 1. Borrow a book" +
                    "\n 2. List of borrowed books" +
                    "\n 3. Return book" +
                    "\n 4. Delete account" +
                    "\n 5. Return to Main menu" +
                    "\n 6. Exit");
            switch (userChoice) {
                case "1" -> bookController.borrowBook();
                case "2" -> bookController.findCustomerBorrowedBooks();
                case "3" -> bookController.returnBook();
                case "4" -> deleteCustomer();
                case "5" -> menuController.start();
                case "6" -> System.exit(0);
                default -> System.out.println("Choose an option from the list!");
            }
        }
        if (account.equals("2")){
            String optionToCreateAnAccount = JOptionPane.showInputDialog(
                    "Would you like to create an account with us? " +
                    "\n 1. Yes" +
                    "\n 2. No");
            if(optionToCreateAnAccount.equals("1")){
                createCustomer();
            }
            if (optionToCreateAnAccount.equals("2")){
                menuController.start();
            }
        }
    }
    public void createCustomer(){
        MenuController menuController = new MenuController();
        Customer customer = new Customer();
        String fName = JOptionPane.showInputDialog("Please enter your first name: ");
        customer.setFirstName(fName);
        String surname = JOptionPane.showInputDialog("Please enter your surname: ");
        customer.setSurname(surname);
        customerRepository.createCustomer(customer);
        String userChoice =JOptionPane.showInputDialog("Thank you for Joining our library!"+
                "\nCustomer ID: "+customer.getId()+
                "\nCustomer first name: " + customer.getFirstName() +
                "\nCustomer Surname: " + customer.getSurname() +
                "\nHas been created!"+
                "\n1. Return to the main menu" +
                "\n2. Exit");
        switch (userChoice) {
            case "1" -> menuController.start();
            case "2" -> System.exit(0);
        }
    }
    public void deleteCustomer(){
        MenuController menuController = new MenuController();
        List<Customer> customerList;
        Customer customer;
        String surname = JOptionPane.showInputDialog("Please enter your surname: ");
        customerList = customerRepository.searchCustomerByName(surname);
        customerRepository.viewFoundCustomerList(customerList);
        Long id = Long.valueOf(JOptionPane.showInputDialog("Please enter your customer ID: "));
        customer = customerRepository.findCustomerById(id);
        customerRepository.deleteCustomer(customer);
        String userChoice = JOptionPane.showInputDialog("This customer has been removed from the database: "+
                         "\nCustomer ID: "+customer.getId()+
                         "\nCustomer first name: " + customer.getFirstName() +
                         "\nCustomer Surname: " + customer.getSurname() +
                         "\nPlease Choose an option"+
                         "\n1. Return to the main menu" +
                         "\n2. Exit");
        switch (userChoice) {
            case "1" -> menuController.start();
            case "2" -> System.exit(0);
        }

    }

}
