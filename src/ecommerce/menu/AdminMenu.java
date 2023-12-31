package ecommerce.menu;

import java.util.Scanner;

import ecommerce.authentication.Authentication;
import ecommerce.client.input.ClientInput;
import ecommerce.store.Store;

public class AdminMenu implements Menu {
	
	public static Scanner input = new Scanner(System.in);
	
	public AdminMenu() {
		
	}

	@Override
	public void displayMenu() {
		int selectedOption;
		String userName = Authentication.currentSession.getCurrentUser().getFullName();
		System.out.println("\n");
		System.out.println("Hello "+userName+",");
		System.out.println("Welcome to your dashboard.");
		System.out.println("********************************");
		System.out.println("1.\tAdd product");
		System.out.println("2.\tShow all products");
		System.out.println("3.\tSearch products");
		System.out.println("4.\tUpdate product");
		System.out.println("5.\tRemove product");
		System.out.println("6.\tLogout");
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
				if(result >0 && result<7) {
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
		CategoryMenu categoryMenu = new CategoryMenu();
		AdminMenu adminMenu = new AdminMenu();
		UpdateProductMenu updateProductMenu = new UpdateProductMenu();
		FilterMenu filterMenu = new FilterMenu();
		ClientInput clientInput = new ClientInput();
		switch(option) {
		case 1:
			categoryMenu.displayMenu();
			break;
		case 2:
			store.displayAllProducts(true);
			adminMenu.displayMenu();
			break;
		case 3:
			filterMenu.displayMenu();
			break;
		case 4:
			updateProductMenu.displayMenu();
			break;
		case 5:
			clientInput.readRefThenRemoveProduct();
			adminMenu.displayMenu();
			break;
		case 6:
			Auth.Logout();
			mainMenu.displayMenu();
			break;
		default:
			System.out.println("The system has crashed, please retry later");
		}
	}

}
