package ecommerce.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ecommerce.authentication.User;
import ecommerce.reviews.Review;
import ecommerce.store.Product;
public class Phone extends Product {
	
	private String brand;
	private String model;
	private String color;
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Phone(String category, String brand, String model, String color, double price, int quantity) {
		this.setCategory(category);
		this.setPrice(price);
		this.setQuantity(quantity);
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.setProductRef(this.generateRef());
		this.setReviews(new HashMap<User, Review>());
	}
	
	public Phone(Phone phone) {
		this.setProductRef(phone.getProductRef());
		this.setCategory(phone.getCategory());
		this.setPrice(phone.getPrice());
		this.setDiscount(phone.getDiscount());
		this.setQuantity(phone.getQuantity());
		this.brand = phone.getBrand();
		this.model = phone.getModel();
		this.color = phone.getColor();
		if (phone.getReviews() == null) {
			this.setReviews(new HashMap<User, Review>());
		}else {
			this.setReviews(phone.getReviews());
		}
	}
	
	@Override
	public String generateRef() {
		// TODO Auto-generated method stub
		return this.getCategory()+"_"+this.brand+"_"+this.model+"_"+this.color;
	}
	
	@Override
	public String toString(boolean displayQuantity) {
		String overallReview = "";
		if (this.getReviews().size() > 0) {
			overallReview = String.valueOf(this.getOverallProductReview()) + " out of 5";
		}else {
			overallReview = "This product doesn't have any review yet";
		}
		if (displayQuantity) {
			return "Ref -> "+this.getProductRef()+"\t|\tCategory -> "+this.getCategory()+"\t|\tBrand -> "+this.brand+"\t|\tModel -> "
					+this.model+"\t|\tColor -> "+this.color+"\t|\tQuantity -> "+String.valueOf(this.getQuantity())+"\t|\tPrice(unit) -> "
					+String.valueOf(this.getPrice())+"\t|\tDiscount -> "+String.valueOf(this.getDiscount())+"%"+"\t|\tOverall review -> "+overallReview;
		}else {
			return "Ref -> "+this.getProductRef()+"\t|\tCategory -> "+this.getCategory()+"\t|\tBrand -> "+this.brand+"\t|\tModel -> "
					+this.model+"\t|\tColor -> "+this.color+"\t|\tPrice(unit) -> "+String.valueOf(this.getPrice())+"\t|\tDiscount -> "
					+String.valueOf(this.getDiscount())+"%"+"\t|\tOverall review -> "+overallReview;
		}
	}

}
