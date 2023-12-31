package ecommerce.menu;

import java.util.Scanner;

import ecommerce.authentication.Authentication;

public class RegisterMenu implements Menu {
	
	public static Scanner input = new Scanner(System.in);
	
	public RegisterMenu() {
		// Empty constructor
	}

	@Override
	public void displayMenu() {
		int selectedOption;
		System.out.println("********************************");
		System.out.println("1.\tAdmin");
		System.out.println("2.\tCustomer");
		System.out.println("********************************");
		
		System.out.print("\nPlease choose a profile ( Admin / Customer ) : ");
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
		Authentication Auth = new Authentication();
		MainMenu mainMenu = new MainMenu();
		switch(option) {
		case 1:
			Auth.Register(true);
			System.out.print("\n");
			mainMenu.displayMenu();
			break;
		case 2:
			Auth.Register(false);
			System.out.print("\n");
			mainMenu.displayMenu();
			break;
		default:
			System.out.println("The system has crashed, please retry later");
		}
	}

}
