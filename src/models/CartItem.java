package models;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int amount) {
        if(amount > product.getQuantity()) throw new IllegalArgumentException("The amount is greater than the available quantity");
        this.product = product;
        this.quantity = amount;
    }

    public Product getProduct() {
        return product;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }


}
