package services;
import interfaces.Shippable;
import java.util.List;

public class ShippingService {
    private final double SHIPPING_COST = 10;
    public double calculateShippingCost(List<Shippable> items) {
        double weight = 0;
        for(Shippable item : items) {
             weight += item.getWeight();
        }
        return SHIPPING_COST * weight;
    }

    public void shipItems(List<Shippable> items , double shippingCost ) {
        System.out.println("Shipping items:");
        for(Shippable item : items) {
            System.out.println(" - " + item.getName() + " (" + item.getWeight() + " kg)");
        }
        System.out.println("Total shipping cost: " + shippingCost);
    }

}
