package ecommerce.authentication;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Scanner;

import ecommerce.menu.AdminMenu;
import ecommerce.menu.CustomerMenu;

public class Authentication {
	public static Scanner input = new Scanner(System.in);
	public static Session currentSession = null;
	private static HashMap<String, User> users = new HashMap<String, User>(); // HashMap containing the list of registered users
	
	public Authentication() {
		
	}
	
	public void Register(boolean isAdmin) {
		String fullName;
		String email;
		String pass;
		String retypedPass;
		System.out.print("\n");
		System.out.println("SIGNUP");
		System.out.println("******************************************************");
		System.out.print("\nName : ");
		fullName = input.nextLine();
		System.out.print("\nEmail : ");
		email = input.nextLine();
		while(users.containsKey(email)) {
			System.out.print("\nThere's an account associated with this email. Please try again : ");
			System.out.print("\nEmail : ");
			email = input.nextLine();
		}
		while(true) {
			System.out.print("\nPassword : ");
			pass = input.nextLine();
			System.out.print("\nRetype password : ");
			retypedPass = input.nextLine();
			if(!pass.equals(retypedPass)) {
				System.out.println("Password doesn't match. Retry again");
			}else {
				if (isAdmin) {
					User user = new Admin(email, pass, fullName);
					user.setIsAdmin(true);
					users.put(email, user);
				}else {
					User user = new Customer(email, pass, fullName);
					user.setIsAdmin(false);
					users.put(email, user);
				}
				System.out.println("\n");
				System.out.println("User created successfully.");
				break;
			}
		}
	}
	
	public void Login() {
		String email;
		String pass;
		System.out.print("\n");
		System.out.println("LOGIN");
		System.out.println("******************************************************");
		System.out.print("\nEmail : ");
		email = input.nextLine();
		while(!users.containsKey(email)) {
			System.out.print("\nThere's no account associated with this email. Please try again : ");
			System.out.print("\nEmail : ");
			email = input.nextLine();
		}
		while(true) {
			System.out.print("\nPassword : ");
			pass = input.nextLine();
			if (!pass.equals(users.get(email).getPassword())) {
				System.out.println("Incorrect password, please retry again : ");
			}else {
				User user = users.get(email);
				
				user.setOnline(true);
				currentSession = new Session(user, LocalTime.now());
				if (user.getIsAdmin()) {
					AdminMenu adminMenu = new AdminMenu();
					adminMenu.displayMenu();
				}else {
					CustomerMenu customerMenu = new CustomerMenu();
					customerMenu.displayMenu();
				}
				break;
			}
		}
	}

	public void Logout() {
		System.out.println("\n");
		currentSession.KillSession();
	}
}
