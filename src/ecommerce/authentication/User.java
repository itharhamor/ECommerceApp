package ecommerce.authentication;

import java.util.ArrayList;
import java.util.List;

import ecommerce.payment.Transaction;
import ecommerce.shopping.Cart;
import ecommerce.shopping.Order;
import ecommerce.store.Product;

public class User {
	private String email; //used as unique login
	private String password;
	private String fullName;
	private boolean online = false;
	private boolean isAdmin = false;
	private Cart shoppingCart;
	private List<Transaction> previousPurchases;
	
	// Getters and setters
	public List<Transaction> getPreviousPurchases() {
		return previousPurchases;
	}
	public void setPreviousPurchases(List<Transaction> previousPurchases) {
		this.previousPurchases = previousPurchases;
	}
	public Cart getShoppingCart() {
		return shoppingCart;
	}
	public void setShoppingCart(Cart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	public boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	// Constructors
	public User(String email, String password, String fullName) {
		this.email = email;
		this.password = password;
		this.fullName = fullName;
		this.previousPurchases = new ArrayList<Transaction>();
	}
	
	public User() {
		
	}
	
	public void addTransaction(Transaction tr) {
		this.previousPurchases.add(tr);
	}
	
	public boolean hasShoppingCart() {
		return this.getShoppingCart() != null;
	}
	
	public String displayPreviousPurchases() {
		String result = "";
		if (this.getPreviousPurchases().isEmpty()) {
			result+="\nYou hadn't bought any product yet! try to make some purchases.";
			return result;
		}
		result+="\nPrevious purchases : \n";
		for (Transaction tr:this.getPreviousPurchases()) {
			result+=new String(new char[1000]).replace('\0', '+')+"\n";
			result+=tr.toString()+"\n\n";
			result+=new String(new char[1000]).replace('\0', '+')+"\n\n";
		}
		return result;
	}
	
	public boolean hasBought(Product p) {
		if (this.getPreviousPurchases().isEmpty()) {
			System.out.println("\nPlease try to make some purchases before rating a product");
			return false;
		}
		for (Transaction tr:this.getPreviousPurchases()) {
			Order order = tr.getOrder();
			Cart cart = order.getCart();
			if (cart.itemExists(p.getProductRef())) {
				return true;
			}
		}
		System.out.println("\nYou can only add review on products that you have already bought!");
		return false;
	}
	
}
