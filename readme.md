# Simple E-Commerce System (OOP Project)

This project is a console-based e-commerce simulation built in Java. It serves as a practical demonstration of fundamental Object-Oriented Programming (OOP) principles, including abstraction, inheritance, polymorphism, and encapsulation.

---

## Features

* **Product Management**: Define products with names, prices, and available quantities.
* **Diverse Product Types**:
    * **Expirable Products**: Items like food that have an expiration date.
    * **Non-Expirable Products**: Items like electronics that do not expire.
* **Shipping Logic**:
    * **Shippable Products**: Items that have a weight and require shipping.
    * **Non-Shippable Products**: Digital items or services that do not require shipping.
* **Shopping Cart**: Customers can add multiple products with specific quantities to their cart.
* **Secure Checkout Process**: The checkout process includes robust validation to prevent errors:
    * Checks if the cart is empty.
    * Verifies if the customer has sufficient balance.
    * Ensures products are not out of stock.
    * Checks if any product in the cart has expired.
* **Dynamic Fee Calculation**: Automatically calculates the order subtotal, shipping fees based on weight, and the final paid amount.
* **Interactive Console Menu**: A user-friendly command-line interface to navigate the application's features.

---

##  OOP Concepts Demonstrated

This project was designed to showcase a solid understanding of core OOP concepts:

* **Abstraction**: The `Product` class is an `abstract` class that defines a common template for all product types, forcing subclasses to implement specific behaviors like `isExpired()` and `getWeight()`.
* **Inheritance**: `ExpirableProduct` and `NonExpirableProduct` classes inherit common attributes and methods from the base `Product` class, promoting code reuse.
* **Polymorphism**: The `Cart` and other parts of the system handle different product types (like `ExpirableProduct` and `NonExpirableProduct`) seamlessly through the common `Product` superclass reference.
* **Encapsulation**: All data fields in the model classes (`Product`, `Customer`, etc.) are kept `private`, and access is controlled through public methods (getters/setters), protecting the application's state.

---
