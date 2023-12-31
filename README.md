# E-Commerce Console App

## Overview
This repository contains the code for a Java-based console application simulating core features that closely resemble real-world e-commerce functionalitites.

## Features

### User Authentication
- **Sign up**: A user can sign up as an admin or as a customer. A name, email and double password authentication is demanded. The use of emails associated with existing accounts prompt a message informing the user of an existing account.

- **Log in**: Users log in using their email and password. Only users with an admin account can access admin functionalities.

### Customer Interface
- **Show All Products**: Customers can see all available products, their characteristics and their prices.

- **Dynamic Product Search and Filtering**: Customers can search for products using reference or by applying filters. Customers can choose which filters to apply. Filters include but are not limited to: Category, discount, brand and model. More on discounts later.

- **Add Product to Shopping Cart**: Customers must use a product's reference as well as the desired quantity to add it to their shopping cart. Two types of messages are possible: Shopping cart display in case of success or an out of stock message in case of failure.

- **Access Shopping Cart**: Customers have access to all products in their shopping cart. They are able to manage the existing items by removing, adding or updating quantity. Customers can also clear their shopping carts or move forward to order completion.

- **Order Processing/Payment Processing**: Customers must enter valid card information to go through with their purchase. Order and transaction data are saved and can be accessed by individual customers as past purchases through the customer menu. Three types of messages are possible: Transaction success, failure due to high demand and suggesting to update cart, and finally a network issue error message that occurs at a 10% rate. When a product is purchased, the store's stock is, of course, updated to represent the remaining quantity (Only accessible by Admins).

- **See Past Purchases**: Customers can see records of their past orders and transactions. Display includes: Date and time of transaction and order details for each individual order.

- **Optional Feature: Reviews**: Customers can only leave reviews on purchased items. A review consists of a rating/5 as well as a message. Each customer can leave one review for each product. Customers can update or delete their past reviews. They can access reviews on different products by reference where profile names, ratings and messages are displayed. The average rating is displayed when searching for products.

- **Log Out**.

## Admin Interface

- **Show All Products**: Admins can see all available products, their characteristics and their price. Admins can also see remaining quantity for each product.

- **Product Management**: Admins can manage products by performing CRUD operations.

- **Optional Feature: Discounts**: Admins can add discounts on products and update existing discounts (0%-90%)

- **Inventory Management**:Admins can manage their inventory by updating product quantity.

- **Log Out**.

## Further Details:

- A `User` class was implemented which was extended to `Admin` and `Customer` classes.

- An abstract `Product` class was implemented to regroup common product functionalities as well as product reviews. Conceptually, many specific categories of products can be added by extending this class. At this time, only two are available(Laptop/Phone).

- Different classes such as `Store`,`Order`,`Transaction`,`Cart` and `Review` were implemented to control different class-specific functionalities.

- An `Authentication` class was implemented to control register, login and logout functionalities. At login, static instance variable currentSession of type `Session` is updated to describe the current user and therefore the appropriate options for said user (more on this later.) At logout, the session is killed and static instance variable currentSession is set to null. At register, the list of registered users is updated.

- A `Session` class was implemented to keep track of current session: user, status (active or not) and date of creation. A session is instantiated at login and used throughout the code to fetch the current user. A session is killed at logout.

- Each feature implementing an interactive menu is represented by a class implementing a `Menu` interface. Each Menu class, of course, overrides the interface methods: A **displayMenu()* method that displays menu and reads user input, a **validateOption()* method that controls user input and a **performAction()* method that deals with the input accordingly by provoking different methods.

- For the sake of code clarity, a `ClientInput` class was implemented to control every user input. Success or failure messages are displayed accordingly.


## Usage
- To run the E-Commerce Console App, execute the `MainMenu.java` file. A menu based interface to interact with the app will be displayed.


