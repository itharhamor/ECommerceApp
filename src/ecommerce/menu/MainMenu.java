package ecommerce.menu;

import java.util.Scanner;

import ecommerce.authentication.Authentication;

public class MainMenu implements Menu{
	
	public static Scanner input = new Scanner(System.in);
	
	public MainMenu() {
		//Empty constructor
	}
	@Override
	public void displayMenu() {
		int selectedOption;
		System.out.println("Welcome to our brand new e-commerce app :)\n");
		System.out.println("********************************");
		System.out.println("1.\tRegister");
		System.out.println("2.\tLogin");
		System.out.println("3.\texit");
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
				if(result >0 && result<4) {
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
		RegisterMenu registerMenu = new RegisterMenu();
		switch(option) {
		case 1:
			System.out.println("\n");
			registerMenu.displayMenu();
			break;
		case 2:
			Auth.Login();
			break;
		case 3:
			System.out.println("Thank you for using our application");
			break;
		default:
			System.out.println("The system has crashed, please retry later");
		}
	}
}
