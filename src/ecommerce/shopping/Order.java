package ecommerce.shopping;

import java.time.LocalDateTime;

import ecommerce.authentication.Authentication;
import ecommerce.authentication.User;
import ecommerce.client.input.ClientInput;
import ecommerce.payment.Transaction;
import ecommerce.store.Product;

public class Order {
	
	private Cart cart;
	private User user;
	private Transaction transaction;
	
	
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	public Order(Cart cart, User user) {
		this.cart = cart;
		this.user = user;
		this.transaction = null;
	}
	
	public Order(Order order) {
		this.cart = order.cart;
		this.user = order.user;
		this.transaction = order.transaction;
	}
	
	public boolean payInvoice() {
		ClientInput clientInput = new ClientInput();
		User user = Authentication.currentSession.getCurrentUser();
		if(!this.cart.itemsAreAvailable()) {
			return false;
		}
		Transaction tr = clientInput.readCardInformation(this);
		if (tr == null) {
			return false;
		}
		this.setTransaction(tr);
		if (!this.getTransaction().getIsSuccess()) {
			System.out.println("Transaction failed due to network problem, please retry again");
			return false;
		}
		System.out.println(this.getTransaction().toString());
		user.addTransaction(tr);
		this.cart.deleteCartItemsFromStore();
		this.cart.clear();
		return true;
	}
	
	public String toString() {
		String result = "";
		result+="Order details : \n";
		result+="**********************\n";
		for(Product p : this.cart.getItems().values()) {
			String item = p.toString(true) + "\t|\tTotal Price -> "+String.valueOf(p.calculateTotalPrice())+"\n";
			result+=item;
			result+=new String(new char[1000]).replace('\0', '-')+"\n";
		}
		
		result+="\n Total payment amount : "+String.valueOf(this.cart.calculateTotal())+" TND";
		return result;
	}
	
}
