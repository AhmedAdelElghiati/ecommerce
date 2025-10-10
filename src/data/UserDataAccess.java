package data;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Customer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDataAccess {

    private static final String FILE_PATH = "src/data/customers.json";
    private static final Gson gson = new Gson();
    private static final Type listType = new TypeToken<ArrayList<Customer>>(){}.getType();
    private static List<Customer> customers;

    public static List<Customer> loadCustomers(){
        try(FileReader reader = new FileReader(FILE_PATH)) {
            customers = gson.fromJson(reader, listType);
        } catch (IOException e){
            e.getMessage();
        }
        return customers != null ? customers : new ArrayList<>();
    }

    public static void writeCustomers(List<Customer> customers){
        try(FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(customers, writer);
        } catch (IOException e){
            e.getMessage();
        }
    }


}