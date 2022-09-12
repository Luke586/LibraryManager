package controller;

import dto.Customer;
import repository.CustomerRepository;

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
                    "\n 2. Return a book" +
                    "\n 3. Delete account" +
                    "\n 4. Return to Main menu" +
                    "\n 5. Exit");
            String userChoice = scanner.nextLine();
            switch (userChoice) {
                case "1" -> bookController.borrowBook();
//              case "2" -> bookRepository.returnBook();
                case "3" -> deleteCustomer();
                case "4" -> menuController.start();
                case "5" -> System.exit(0);
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
    }

    public void deleteCustomer(){

        System.out.println("Please enter customer ID: ");
        Long id = scanner.nextLong();
        Customer customer = customerRepository.findCustomerById(id);
        customerRepository.deleteCustomer(customer);
    }

}
