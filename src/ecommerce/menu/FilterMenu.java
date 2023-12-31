package ecommerce.menu;

import java.util.Scanner;

import ecommerce.authentication.Authentication;
import ecommerce.client.input.ClientInput;
import ecommerce.store.Store;

public class FilterMenu implements Menu {
	
	public static Scanner input = new Scanner(System.in);
	
	public FilterMenu() {
		
	}

	@Override
	public void displayMenu() {
		int selectedOption;
		String userName = Authentication.currentSession.getCurrentUser().getFullName();
		System.out.println("\n");
		System.out.println("********************************");
		System.out.println("1.\tSearch by reference");
		System.out.println("2.\tApply filters");
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
				if(result >0 && result<3) {
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
		MainMenu mainMenu = new MainMenu();
		CategoryMenu categoryMenu = new CategoryMenu();
		AdminMenu adminMenu = new AdminMenu();
		CustomerMenu customerMenu = new CustomerMenu();
		UpdateProductMenu updateProductMenu = new UpdateProductMenu();
		ClientInput clientInput = new ClientInput();
		switch(option) {
		case 1:
			clientInput.readThenSearchProductByRef();
			if (Authentication.currentSession.getCurrentUser().getIsAdmin()) {
				adminMenu.displayMenu();
			}else {
				customerMenu.displayMenu();
			}
			break;
		case 2:
			clientInput.readThenApplyFilters();
			if (Authentication.currentSession.getCurrentUser().getIsAdmin()) {
				adminMenu.displayMenu();
			}else {
				customerMenu.displayMenu();
			}
			break;
		default:
			System.out.println("The system has crashed, please retry later");
		}
	}

}
