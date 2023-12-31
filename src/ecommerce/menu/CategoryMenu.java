package ecommerce.menu;

import java.util.Scanner;

import ecommerce.authentication.Authentication;
import ecommerce.client.input.ClientInput;

public class CategoryMenu implements Menu{
	
	public static Scanner input = new Scanner(System.in);
	
	public CategoryMenu() {
		
	}

	@Override
	public void displayMenu() {
		int selectedOption;
		System.out.println("\n");
		System.out.println("********************************");
		System.out.println("1.\tLaptop");
		System.out.println("2.\tPhone");
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
		ClientInput clientInput = new ClientInput();
		AdminMenu adminMenu = new AdminMenu();
		switch(option) {
		case 1:
			clientInput.readThenCreateLaptop();
			adminMenu.displayMenu();
			break;
		case 2:
			clientInput.readThenCreatePhone();
			adminMenu.displayMenu();
			break;
		default:
			System.out.println("The system has crashed, please retry later");
		}
	}

	
}
