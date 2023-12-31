package ecommerce.store;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import ecommerce.authentication.User;
import ecommerce.reviews.Review;

public abstract class Product {
	private String productRef;
	private String category;
	private double price;
	private int discount;
	private int quantity;
	private HashMap<User, Review> reviews;
	
	
	public String getProductRef() {
		return productRef;
	}
	public void setProductRef(String productRef) {
		this.productRef = productRef;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getDiscount() {
		return discount;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public HashMap<User, Review> getReviews() {
		return reviews;
	}
	public void setReviews(HashMap<User, Review> reviews) {
		this.reviews = reviews;
	}
	
	public abstract String generateRef();
	public abstract String toString(boolean displayQuantity);
	
 	private void updateQuantity(int quantity) {
		this.quantity += quantity;
	}
	public Product addToStore() {
		String ref = this.productRef;
		if (Store.getProducts().containsKey(ref)) {
			Product p = Store.getProducts().get(ref);
			p.updateQuantity(this.getQuantity());
			Store.getProducts().replace(ref, p);
			return p;
		}
		Store.getProducts().put(ref, this);
		return this;
	}
	
	public void incrementQuantity(int quantity) {
		this.setQuantity(this.getQuantity() + quantity);
	}
	
	public double calculateTotalPrice() {
		return this.price * this.quantity;
	}
	
	public Product updateProductQuantity(int quantity) {
		String ref = this.productRef;
		if (Store.getProducts().containsKey(ref)) {
			this.setQuantity(quantity);
			Store.getProducts().replace(ref, this);
			return this;
		}
		return null;
	}
	
	public Product updateProductPrice(double price) {
		String ref = this.productRef;
		if (Store.getProducts().containsKey(ref)) {
			this.setPrice(price);
			Store.getProducts().replace(ref, this);
			return this;
		}
		return null;
	}
	
	public Product updateProductDiscount(int discount) {
		String ref = this.productRef;
		double priceWithoutDiscount = this.price/(1-(double)this.discount/100);
		if (Store.getProducts().containsKey(ref)) {
			if (this.discount > 0) {
				this.price = priceWithoutDiscount;
			}
			this.discount = discount;
			if (this.discount>0) {
				this.price = this.price - this.price*((double)discount/100);
			}
			Store.getProducts().replace(ref, this);
			return this;
		}
		return null;
	}
	
	public void buyProduct() {
		String ref = this.productRef;
		int quantity = this.quantity;
		Product p = Store.getProducts().get(ref);
		p.setQuantity(p.getQuantity() - quantity);
		if (p.getQuantity() <= 0) {
			this.RemoveProductFromStore();
		}
	}

	public Product RemoveProductFromStore() {
		String ref = this.productRef;
		if (Store.getProducts().containsKey(ref)) {
			Store.getProducts().remove(ref);
			return this;
		}
		return null;
	}
	
	public double getOverallProductReview() {
		double overallReview = 0;
		for (Review r:this.getReviews().values()) {
			overallReview += r.getRating();
		}
		if (this.getReviews().size()>0) {
			overallReview = overallReview / this.getReviews().size();
		}
		return overallReview;
	}
	
	public String displayReviews() {
		String result = "\n";
		if (this.getReviews().size() == 0) {
			result+="No reviews yet for product "+this.getProductRef();
			return result;
		}
		for (Review r:this.getReviews().values()) {
			result+=new String(new char[1000]).replace('\0', '+')+"\n";
			result+=r.toString()+"\n\n";
			result+=new String(new char[1000]).replace('\0', '+')+"\n\n";
		}
		return result;
	}
}
