package models;
import interfaces.Expirable;
import interfaces.Shippable;
import java.time.LocalDate;

public class ExpirableProduct extends Product implements Shippable {
    private LocalDate expirationDate;
    private double weight;
    public ExpirableProduct(String name, double price, int quantity, LocalDate expirationDate, double weight) {
        super(name, price, quantity);
        this.expirationDate = expirationDate;
        this.weight = weight;
    }
    public LocalDate getExpirationDate() {
        return expirationDate;
    }
    @Override
    public boolean isExpired() {
        return expirationDate.isBefore(LocalDate.now());
    }
    @Override
    public double getWeight() {
        return weight;
    }
}
