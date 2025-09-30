package util;
import models.ExpirableProduct;
import models.NonExpirableProduct;
import models.Product;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ProductInitializer {
    public static Map<String , Product> Products() {
        Map<String , Product> products = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        products.put(
                "Cheese" ,
                new ExpirableProduct("Cheese" , 200 , 100 , LocalDate.of(2025, 12, 31) , 1)
        );

        products.put(
                "Tv",
                new NonExpirableProduct("TV" , 10000 , 10,10)
        );

        products.put(
                "MobileCard",
                new NonExpirableProduct("mobileCard" , 150 , 100)
        );

        return products;
    }
}
