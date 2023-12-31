package ecommerce.menu;

import java.util.Scanner;

import ecommerce.authentication.Authentication;
import ecommerce.client.input.ClientInput;
import ecommerce.shopping.Cart;
import ecommerce.store.Product;
import ecommerce.store.Store;

public class CustomerMenu implements Menu {
	
	public static Scanner input = new Scanner(System.in);
	
	public CustomerMenu() {
		
	}

	@Override
	public void displayMenu() {
		if (!Authentication.currentSession.getCurrentUser().hasShoppingCart()) {
			Authentication.currentSession.initUserCart();
		}
		int selectedOption;
		String userName = Authentication.currentSession.getCurrentUser().getFullName();
		System.out.println("\n");
		System.out.println("Hello "+userName+",");
		System.out.println("Welcome to your dashboard.");
		System.out.println("********************************");
		System.out.println("1.\tAdd product to shopping cart");
		System.out.println("2.\tShow all products");
		System.out.println("3.\tSearch products");
		System.out.println("4.\tMy shopping cart");
		System.out.println("5.\tPrevious purchases");
		System.out.println("6.\tReviews");
		System.out.println("7.\tLogout");
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
				if(result >0 && result<8) {
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
		Authentication Auth = new Authentication();
		Store store = new Store();
		MainMenu mainMenu = new MainMenu();
		CustomerMenu customerMenu = new CustomerMenu();
		CartMenu cartMenu = new CartMenu();
		FilterMenu filterMenu = new FilterMenu();
		ReviewsMenu reviewsMenu = new ReviewsMenu();
		ClientInput clientInput = new ClientInput();
		switch(option) {
		case 1:
			Product p = clientInput.readRefQuantity();
			Cart cart = Authentication.currentSession.getCurrentUser().getShoppingCart();
			cart.addItem(p);
			customerMenu.displayMenu();
			break;
		case 2:
			store.displayAllProducts(false);
			customerMenu.displayMenu();
			break;
		case 3:
			filterMenu.displayMenu();
			break;
		case 4:
			System.out.println(Authentication.currentSession.getCurrentUser().getShoppingCart().toString());
			if (Authentication.currentSession.getCurrentUser().getShoppingCart().getItems().isEmpty()) {
				customerMenu.displayMenu();
			}else {
				cartMenu.displayMenu();
			}
			break;
		case 5:
			System.out.println(Authentication.currentSession.getCurrentUser().displayPreviousPurchases());
			customerMenu.displayMenu();
			break;
		case 6:
			reviewsMenu.displayMenu();
		case 7:
			Auth.Logout();
			mainMenu.displayMenu();
			break;
		default:
			System.out.println("The system has crashed, please retry later");
		}
	}

}
