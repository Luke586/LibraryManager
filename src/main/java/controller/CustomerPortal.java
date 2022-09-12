package controller;

import dto.Customer;
import org.hibernate.SessionFactory;
import repository.BookRepository;
import repository.CustomerRepository;

import java.util.Scanner;

public class CustomerPortal {


    static SessionFactory factory;

    private final Scanner scanner = new Scanner(System.in);

    BookRepository bookRepository = new BookRepository(factory);

    CustomerRepository customerRepository = new CustomerRepository(factory);

    MenuController menuController;


    public void customerPortal() {
        Customer customer = new Customer();
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
//                    case "1":
//                        bookRepository.borrowBook();
//                        break;
//                    case "2":
//                        bookRepository.returnBook();
//                        break;
                case "3":
                    deleteCustomer();
                    break;
                case "4":
                    menuController.start();
                    break;
                case "5":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choose an option from the list!");
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
                this.menuController.start();
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
