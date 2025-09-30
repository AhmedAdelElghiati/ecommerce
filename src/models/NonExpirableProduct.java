package models;
import interfaces.Shippable;

public class NonExpirableProduct extends Product implements Shippable {
    private double weight;

    // Create shippable product
    public NonExpirableProduct(String name, double price, int quantity , double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }
    //  Create unshippable product
    public NonExpirableProduct(String name, double price, int quantity) {
        super(name, price, quantity);
        this.weight = 0;
    }
    @Override
    public double getWeight() {
        return weight;
    }
    @Override
    public boolean isExpired() {
        return false;
    }
}
