package controller;

import dto.Customer;
import repository.CustomerRepository;

import java.util.List;
import java.util.Scanner;

public class CustomerPortal {


    private final Scanner scanner = new Scanner(System.in);

    private final BookController bookController = new BookController();

    private final CustomerRepository customerRepository = new CustomerRepository();

    public void customerPortal() {
        MenuController menuController = new MenuController();
        System.out.println("Do you have an account with us? " +
                "\n 1. Yes" +
                "\n 2. No");
        String account = scanner.nextLine();
        if (account.equals("1")) {
            System.out.println("Would you like to: " +
                    "\n 1. Borrow a book" +
                    "\n 2. List of borrowed books" +
                    "\n 3. Return book" +
                    "\n 4. Delete account" +
                    "\n 5. Return to Main menu" +
                    "\n 6. Exit");
            String userChoice = scanner.nextLine();
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
            System.out.println("Would you like to create an account with us? " +
                    "\n 1. Yes" +
                    "\n 2. No");
            String optionToCreateAnAccount = scanner.nextLine();
            if(optionToCreateAnAccount.equals("1")){
                createCustomer();
            }
            if (optionToCreateAnAccount.equals("2")){
                menuController.start();
            }
        }
    }
    public void createCustomer(){
        Customer customer = new Customer();
        System.out.println("Please enter your first name: ");
        String fName = scanner.nextLine();
        customer.setFirstName(fName);
        System.out.println("Please enter your surname: ");
        String surname = scanner.nextLine();
        customer.setSurname(surname);
        customerRepository.createCustomer(customer);
//        customerRepository.displayNewlyCreatedCustomer(customer);
        System.out.println("Thank you for Joining our library!");
        System.out.println("Customer ID: "+customer.getId()+
                           "\nCustomer first name: " + customer.getFirstName() +
                           "\nCustomer Surname: " + customer.getSurname() +
                           "\nHas been created!" +
                           "\n");
    }

    public void deleteCustomer(){
        List<Customer> customerList;
        Customer customer;
        System.out.println("Please enter your surname: ");
        String surname = scanner.nextLine();
        customerList = customerRepository.searchCustomerByName(surname);
        customerRepository.viewFoundCustomerList(customerList);
        System.out.println("Please enter your customer ID: ");
        Long id = scanner.nextLong();
        customer = customerRepository.findCustomerById(id);
        customerRepository.deleteCustomer(customer);
        System.out.println("Has been removed from the database!" +
                           "\n");
    }

}
