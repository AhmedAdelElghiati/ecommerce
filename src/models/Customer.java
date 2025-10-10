package models;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String mobileNumber;
    private double balance;
    private String passwordHash;

    public Customer(int id, String name, String email , String mobileNumber, double balance, String passwordHash) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.balance = balance;
        this.passwordHash = passwordHash;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getEmail() {return email;}

    public boolean hasBalance(double amount) {
        return balance >= amount;
    }

    public void reduceBalance(double amount) {
        if (!hasBalance(amount)) {
            throw new IllegalStateException("You don't have enough balance");
        }
        this.balance -= amount;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static String hash(String password) {
        return Integer.toHexString(password.hashCode());
    }

    public boolean checkPassword(String password) {
        return this.passwordHash.equals(hash(password));
    }
}
