package util;

import models.Cart;
import models.Customer;
import models.Product;
import services.CustomerService;

import java.util.Map;
import java.util.Scanner;

public class ApplicationController {

    private static final String SEPARATOR = ("-".repeat(50));
    private final Scanner scanner;

    public ApplicationController(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        try {
            Customer customer = showWelcomeAndAuthenticate();
            if(customer != null) {
                runShoppingSession(customer);
            } else {
                System.out.println("Something went wrong during authentication.");
            }
        } catch (Exception e) {
            System.out.println("Something went wrong." + e.getMessage());
        }
    }

    private Customer showWelcomeAndAuthenticate() {
        Customer customer = null;
        System.out.println(SEPARATOR);
        System.out.printf("%35s %n", "Welcome to our store");
        System.out.println(SEPARATOR);
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Enter your choice: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. please enter a number (1 | 2).");
            scanner.nextLine();
            return null;
        }

        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> customer = register();
            case 2 -> customer = login();
            default -> System.out.println("Invalid input. please select (1 | 2).");
        }
        return customer;
    }

    private Customer register() {
        try {
            System.out.println(SEPARATOR);
            System.out.printf("%34s %n", "Register Customer ");
            System.out.println(SEPARATOR);

            System.out.print("Please enter your name: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Name can't be empty. Please enter a valid name.");
                return null;
            }
            System.out.print("Please enter your email: ");
            String email = scanner.nextLine().trim();
            if (!email.contains("@") || !email.contains(".") || email.length() < 7 || email.isEmpty()) {
                System.out.println("Invalid email. Please enter a valid email.");
                return null;
            }
            System.out.print("Please enter your phone number: ");
            String mobileNumber = scanner.nextLine().trim();
            if (mobileNumber.isEmpty()) {
                System.out.println("Phone number can't be empty. Please enter a valid phone number.");
                return null;
            }
            System.out.print("Please enter your balance: ");
            if (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid balance.");
                scanner.nextLine();
                return null;
            }
            double balance = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Please enter your password (at least 6 characters) : ");
            String password = scanner.nextLine();
            if (password.length() < 6) {
                System.out.println("Password must be at least 6 characters long.");
                return null;
            }
            return CustomerService.register(name, email, mobileNumber, balance, password);
        } catch (Exception e) {
            System.out.println("Something went wrong during registration." + e.getMessage());
            return null;
        }
    }

    private Customer login() {
        try {
            System.out.println(SEPARATOR);
            System.out.printf("%34s %n", "Login Customer ");
            System.out.println(SEPARATOR);

            System.out.print("Please enter your email: ");
            String email = scanner.nextLine().trim();
            System.out.print("Please enter your password: ");
            String password = scanner.nextLine();

            Customer customer = CustomerService.login(email, password);
            if (customer != null) {
                System.out.println(SEPARATOR);
                System.out.println("Login successful!");
                System.out.println(SEPARATOR);
            } else {
                System.out.println("Login failed. Invalid email or password.");
            }
            return customer;
        } catch (Exception e) {
            System.out.println("Something went wrong during login." + e.getMessage());
            return null;
        }
    }

    private void runShoppingSession(Customer customer) {
        try {
            System.out.println("Welcome " + customer.getName());
            System.out.println("Your balance is " + customer.getBalance());
            Map<String , Product> products = ProductInitializer.Products();
            Cart cart = new Cart(customer);

            boolean running = true;
            while(running) {
                InputUtil.viewMenu();
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. please enter a number (1 | 2 | 3 | 4 | 5).");
                    scanner.nextLine();
                    continue;
                }
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        InputUtil.viewProducts(products);
                        break;
                    case 2:
                        try {
                            Object[] item = InputUtil.addProductToCart(products);
                            cart.add((Product) item[0] , (int) item[1]);
                            System.out.println(SEPARATOR);
                            System.out.println("Product added to cart successfully");
                            System.out.println(SEPARATOR);
                        } catch (Exception e) {
                            System.out.println("An error occurred during adding product to cart: " + e.getMessage());
                            continue;
                        }
                        break;
                    case 3:
                        try {
                            InputUtil.viewCartItems(cart);
                        } catch (Exception e) {
                            System.out.println("An error occurred during viewing cart items: " + e.getMessage());
                            continue;
                        }
                        break;
                    case 4:
                        try {
                            cart.checkOut();
                        } catch (Exception e) {
                            System.out.println("An error occurred during checkout: " + e.getMessage());
                            continue;
                        }
                        break;
                    case 5:
                        System.out.println("Thank you for shopping with us");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice please try again");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong during shopping session." + e.getMessage());
            scanner.nextLine();
        }
    }
}
