package util;
import models.Cart;
import models.ExpirableProduct;
import models.Product;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class InputUtil {
    static Scanner scanner = new Scanner(System.in);
    static String SEPERATOR = ("-".repeat(50));
    public static void viewMenu() {
        System.out.println(SEPERATOR);
        System.out.println("1. View Available Products");
        System.out.println("2. Add Product to Cart");
        System.out.println("3. View Cart Items");
        System.out.println("4. Check Out");
        System.out.println("5. Exit");
        System.out.println(SEPERATOR);
        System.out.print("Enter your choice: ");
    }
    public static void viewProducts (Map<String , Product> products) {
        if (products.isEmpty() ) {
            System.out.println(SEPERATOR);
            System.out.println("No products available to display.");
            System.out.println(SEPERATOR);
        }
        String border = "+-------------------------+----------+----------+-----------------+-------------+-------------+";
        String header = "| %-23s | %-8s | %-8s | %-15s | %-11s | %-9s |%n";

        System.out.println("\nAvailable Products List:");
        System.out.println(border);
        System.out.printf(header, "Product Name", "Price ($)", "Stock", "Expiration Date", "Weight (kg)" , "Shippable?");
        System.out.println(border);
        for(Product product : products.values()) {
            String expirationDateStr;
            if (product instanceof ExpirableProduct) {
                LocalDate expDate = ((ExpirableProduct) product).getExpirationDate();
                expirationDateStr = expDate.toString();
            } else {
                expirationDateStr = "N/A";
            }

            System.out.printf("| %-23s | %-8.2f | %-8d | %-15s | %-11.2f | %-11s |%n",
                    product.getName(),
                    product.getPrice(),
                    product.getQuantity(),
                    expirationDateStr,
                    product.getWeight(),
                    product.getWeight() != 0 ? "Yes" : "No"
                    );
        }
        System.out.println(border);
    }
    public static Object[] addProductToCart(Map<String , Product> products) {
        String productName;
        while (true) {
            System.out.print("Enter the product name: ");
            productName = scanner.nextLine();
            if (products.containsKey(productName)) {
                break;
            } else {
                System.out.println("Product not found. Please try again.");
            }
        }
        System.out.print("Enter the quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.printf("%d %s added to cart.%n",quantity , productName );
        return new Object[]{products.get(productName) , quantity};
    }
    public static void viewCartItems(Cart cart) {
        cart.viewItems();
    }
}
