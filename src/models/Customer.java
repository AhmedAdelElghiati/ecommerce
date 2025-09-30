package models;
public class Customer {
    private int id;
    private String name;
    private String mobileNumber;
    private double balance;

    public Customer(int id, String name, String mobileNumber , double balance) {
        this.id = id;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.balance = balance;
    }

    public int getId() { return id; }
    public String getName() {
        return name;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public double getBalance() {
        return balance;
    }

    public boolean hasBalance(double amount) {
        return balance >= amount;
    }
    public void reduceBalance(double amount) {
        if(!hasBalance(amount)) {
            throw new IllegalStateException("You don't have enough balance");
        }
        this.balance -= amount;
    }
}
