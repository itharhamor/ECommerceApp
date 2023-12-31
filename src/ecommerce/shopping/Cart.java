package ecommerce.shopping;

import java.util.HashMap;

import ecommerce.authentication.Authentication;
import ecommerce.authentication.User;
import ecommerce.store.Laptop;
import ecommerce.store.Phone;
import ecommerce.store.Product;
import ecommerce.store.Store;

public class Cart {
	private User user;
	private HashMap<String, Product> items;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public HashMap<String, Product> getItems() {
		return items;
	}
	public void setItems(HashMap<String, Product> items) {
		this.items = items;
	}
	
	public Cart(User user) {
		this.user = user;
		this.items = new HashMap<String, Product>();
	}
	
	public Cart(Cart cart) {
		this.setUser(cart.getUser());
		this.setItems(cart.getItems());
	}
	
	public boolean itemExists(String ref) {
		return this.getItems().containsKey(ref);
	}
	
	public boolean clear() {
		if (this == null) {
			return false;
		}
		Authentication.currentSession.initUserCart();
		return true;
	}
	
	public boolean removeItem(Product p) {
		if (p == null) {
			return false;
		}
		Product removedItem = this.getItems().remove(p.getProductRef());
		if (removedItem == null) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean deleteCartItemsFromStore() {
		for(Product p:this.items.values()) {
			p.buyProduct();
		}
		return true;
	}
	
	public boolean updateItemQuantity(String ref, int quantity) {
		Product p = this.getItems().get(ref);
		if (p == null) {
			return false;
		}else {
			p.setQuantity(quantity);
			return true;
		}
	}
	
	public HashMap<String, Product> addItem(Product p){
		HashMap<String, Product> result = new HashMap<String, Product>();
		Store store = new Store();
		if (store.isOutOfStock(p)) {
			System.out.println("\nThis product is out of stock :/");
			return result;
		}else {
			if (items.containsKey(p.getProductRef())) {
				Product p1 = items.get(p.getProductRef());
				p1.setQuantity(p1.getQuantity() + p.getQuantity());
				items.replace(p.getProductRef(), p1);
				System.out.println(this.toString());
				return items;
				}else {
					items.put(p.getProductRef(), p);
					System.out.println(this.toString());
					return items;
					}
				}
			}
	
	public boolean itemsAreAvailable() {
		System.out.println("\n");
		boolean itemsAreAvailable = true;
		Store store = new Store();
		for (Product p:items.values()) {
			if(store.isOutOfStock(p)) {
				itemsAreAvailable = false;
				System.out.println("Product with reference "+p.getProductRef()+" is out of stock. Try to update your shopping cart or retry later.");
			}else {
				if (!store.isQuantitySufficient(p)) {
					itemsAreAvailable = false;
					System.out.println("We are receiving too much demand on product with reference "+p.getProductRef()+". Try to "
							+ "update your shopping cart or retry later.");
				}
			}
		}
		return itemsAreAvailable;
	}
	
	public double calculateTotal() {
		double total = 0;
		for(Product p : items.values()) {
			total+=p.calculateTotalPrice();
		}
		return total;
	}

	@Override
	public String toString() {
		String result = "";
		double total = this.calculateTotal();
		result+="\nShopping Cart : \n";
		result+="**********************\n";
		for(Product p : items.values()) {
			String item = p.toString(true) + "\t|\tTotal Price -> "+String.valueOf(p.calculateTotalPrice())+"\n";
			result+=item;
			result+=new String(new char[1000]).replace('\0', '-')+"\n";
		}
		if (items.isEmpty()) {
			result+="You shopping cart is empty, try to add some products :)";
		}else {
			result+="The total amount to pay is : "+String.valueOf(total)+" TND";
		}
		return result;
	}
}
