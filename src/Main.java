import models.Cart;
import models.Customer;
import models.ExpirableProduct;
import models.NonExpirableProduct;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Customer customer1 = new Customer(001 ,"Ahmed Adel" , "01000274642" , 100000);
        ExpirableProduct cheese = new ExpirableProduct("Cheese" , 200 , 100 , LocalDate.of(2025, 12, 31) , 1);
        NonExpirableProduct tv = new NonExpirableProduct("TV" , 10000 , 10,10);
        NonExpirableProduct mobileCard = new NonExpirableProduct("mobileCard" , 150 , 100);
        Cart cart1 = new Cart(customer1);
        cart1.add(tv , 2);
        cart1.add(cheese , 3);
        cart1.add(mobileCard , 3);
        cart1.checkOut();
    }
}