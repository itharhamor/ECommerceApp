package ecommerce.menu;

import java.util.Scanner;

import ecommerce.authentication.Authentication;
import ecommerce.authentication.User;
import ecommerce.client.input.ClientInput;
import ecommerce.payment.Transaction;
import ecommerce.shopping.Cart;
import ecommerce.shopping.Order;
import ecommerce.store.Product;
import ecommerce.store.Store;

public class CartMenu implements Menu {
	
	public static Scanner input = new Scanner(System.in);
	
	public CartMenu() {
		
	}

	@Override
	public void displayMenu() {
		int selectedOption;
		System.out.println("\n");
		System.out.println("********************************");
		System.out.println("1.\tRemove product from cart");
		System.out.println("2.\tUpdate product quantity");
		System.out.println("3.\tClear cart");
		System.out.println("4.\tGo to checkout");
		System.out.println("5.\tGo to dashboard");
		System.out.println("********************************");
		
		System.out.print("\nPlease choose an option : ");
		selectedOption = this.validateOption();
		this.performAction(selectedOption);
	}

	@Override
	public int validateOption() {
		int result;
		while(true) {
			try{
				result = Integer.parseInt(input.nextLine());
				if(result >0 && result<6) {
					return result;
				}
				System.out.print("\nPlease choose a valid option : ");
			}catch(Exception e) {
				System.out.print("\nPlease choose a valid option : ");			
				}
		}
	}

	@Override
	public void performAction(int option) {
		Store store = new Store();
		CustomerMenu customerMenu = new CustomerMenu();
		CartMenu cartMenu = new CartMenu();
		ClientInput clientInput = new ClientInput();
		switch(option) {
		case 1:
			clientInput.readRefThenDeleteItemFromCart();
			System.out.println(Authentication.currentSession.getCurrentUser().getShoppingCart().toString());
			if (Authentication.currentSession.getCurrentUser().getShoppingCart().getItems().isEmpty()) {
				customerMenu.displayMenu();
			}else {
				cartMenu.displayMenu();
			}
			break;
		case 2:
			clientInput.readRefQuantityThenUpdateCartItem();
			System.out.println(Authentication.currentSession.getCurrentUser().getShoppingCart().toString());
			if (Authentication.currentSession.getCurrentUser().getShoppingCart().getItems().isEmpty()) {
				customerMenu.displayMenu();
			}else {
				cartMenu.displayMenu();
			}
			break;
		case 3:
			Cart shoppingCart = Authentication.currentSession.getCurrentUser().getShoppingCart();
			if (!shoppingCart.clear()) {
				System.out.println("\nProblem encountred when trying to clear the shopping cart");
			}else {
				System.out.println("Shoping cart is cleared successfully");
			}
			customerMenu.displayMenu();
			break;
		case 4:
			User user = Authentication.currentSession.getCurrentUser();
			Cart cart = user.getShoppingCart();
			Order order = new Order(cart, user);
			boolean transactionSucceed = order.payInvoice();
			if (transactionSucceed) {
				customerMenu.displayMenu();
			}else {
				System.out.println(cart.toString());
				this.displayMenu();
			}
			break;
		case 5:
			customerMenu.displayMenu();
			break;
		default:
			System.out.println("The system has crashed, please retry later");
		}
	}
	
	
}
