import models.*;
import util.InputUtil;
import util.ProductInitializer;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String , Product> products = ProductInitializer.Products();
        Customer customer1 = new Customer(001 ,"Ahmed Adel" , "01000274642" , 100000);
        Cart cart = new Cart(customer1);

        boolean running = true;
        while(running) {
            InputUtil.viewMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    InputUtil.viewProducts(products);
                    break;
                case 2:
                    Object[] item = InputUtil.addProductToCart(products);
                     cart.add((Product) item[0] , (int) item[1]);
                     break;
                case 3:
                    InputUtil.viewCartItems(cart);
                    break;
                case 4:
                    cart.checkOut();
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
    }
}