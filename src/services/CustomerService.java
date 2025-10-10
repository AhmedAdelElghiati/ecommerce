package services;

import data.UserDataAccess;
import models.Customer;

import java.util.List;

public class CustomerService {
     public static Customer register(String name, String email, String mobileNumber, double balance ,String password ) {
         List<Customer> customers = UserDataAccess.loadCustomers();
         for (Customer c : customers) {
             if (c.getEmail().equals(email)) {
                 System.out.println("Customer already exists");
             }
         }
         int newID = customers.size() == 0 ? 1 : customers.size() + 1;
         String passwordHash = Customer.hash(password);
         Customer customer = new Customer(newID, name, email, mobileNumber, balance, passwordHash);
         customers.add(customer);
         UserDataAccess.writeCustomers(customers);
         return customer;
     }
     public static Customer login(String email, String password) {
         List<Customer> customers = UserDataAccess.loadCustomers();
         for (Customer c : customers) {
             if (c.getEmail().equals(email) && c.checkPassword(password)) {
                 return c;
             }
         }
         return null;
     }
     public static Customer updateCustomerBalance(Customer customer) {
         List<Customer> customers = UserDataAccess.loadCustomers();
         for (Customer c : customers) {
             if (c.getId() == customer.getId()) {
                 c.setBalance(customer.getBalance());
                 UserDataAccess.writeCustomers(customers);
                 return customer;
             }
         }
         return null;
     }
}
