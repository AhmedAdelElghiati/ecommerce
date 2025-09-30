package models;
import interfaces.Shippable;
import services.ShippingService;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Customer customer;
    private List<CartItem> items = new ArrayList<>();

    public Cart(Customer customer) {
        this.customer = customer;
    }

    public void add(Product product, int amount) {
        if(amount <= 0) throw new IllegalArgumentException("The amount must be greater than zero");
        items.add(new CartItem(product, amount));
    }

    public void checkOut() {
        List<Shippable> shippableItems = new ArrayList<>();
        double amount = 0;
        if(items.isEmpty()) throw new IllegalStateException("The cart is empty");

        for(CartItem item : items) {
            Product product = item.getProduct();
            // check expiration date
            if(product.isExpired()) throw new IllegalStateException("The " + product.getName() + " is expired");
            // check stock
            if(product.getQuantity() < item.getQuantity()) throw new IllegalStateException("The " + product.getName() + "quantity is not enough");
            // fill shippable items list to calculate shipping cost
            if(product.getWeight() > 0 && product instanceof Shippable) {
                shippableItems.add((Shippable) product);
            }
            // increase subtotal price
            amount += item.getTotalPrice();
            // decrease product quantity
            product.decreaseQuantity(item.getQuantity());
        }
        // calculate shipping cost
        ShippingService shippingService = new ShippingService();
        double shippingCost = shippingService.calculateShippingCost(shippableItems);
        // reduce customer balance if available
        customer.reduceBalance(amount + shippingCost);
        // print receipt

        System.out.println("Cart items:");
        for(CartItem item : items) {
            System.out.printf(" - %dx %s (%.2f$)%n", item.getQuantity(), item.getProduct().getName(), item.getTotalPrice());
        }
        if (!shippableItems.isEmpty()) {
            shippingService.shipItems(shippableItems , shippingCost);
        } else {
            System.out.println("No items require shipping.");
        }
        System.out.println("======================================");
        System.out.println("Total price: " + amount + " $");
        System.out.println("Shipping cost: " + shippingCost + " $");
        System.out.println("Final price: " + (amount + shippingCost) + " $");
        System.out.println("Remaining balance in your account: " + customer.getBalance() + " $");
        System.out.println("======================================");
        items.clear();
    }
}
