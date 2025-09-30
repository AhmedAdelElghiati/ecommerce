package models;
public abstract class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {return price;}
    public int getQuantity() {
        return quantity;
    }

    public void decreaseQuantity(int amount) {
        if(amount > quantity) throw new IllegalArgumentException("The amount is greater than the available quantity");
        this.quantity -= amount;
    }

    public abstract boolean isExpired();

    public abstract double getWeight();

    @Override
    public String toString() {
        return "[name=" + name + ", price=" + price + ", quantity=" + quantity + "]";
    }

}
