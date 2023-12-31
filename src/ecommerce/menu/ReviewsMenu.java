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

public class ReviewsMenu implements Menu {
	
	public static Scanner input = new Scanner(System.in);
	
	public ReviewsMenu() {
		
	}

	@Override
	public void displayMenu() {
		int selectedOption;
		System.out.println("\n");
		System.out.println("********************************");
		System.out.println("1.\tAdd review");
		System.out.println("2.\tUpdate review");
		System.out.println("3.\tRemove review");
		System.out.println("4.\tDisplay reviews");
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
			clientInput.readReviewThenAdd();
			this.displayMenu();
			break;
		case 2:
			clientInput.readReviewThenUpdate();
			this.displayMenu();
			break;
		case 3:
			clientInput.readReviewThenDelete();
			this.displayMenu();
			break;
		case 4:
			clientInput.displayReviews();
			customerMenu.displayMenu();
			break;
		case 5:
			customerMenu.displayMenu();
			break;
		default:
			System.out.println("The system has crashed, please retry later");
		}
	}
	
	
}
