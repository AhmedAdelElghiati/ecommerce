import models.*;
import util.ApplicationController;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ApplicationController controller = new ApplicationController(scanner);
            controller.run();
        }
    }
}