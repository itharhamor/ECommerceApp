package ecommerce.store;

import java.util.HashMap;

import ecommerce.authentication.User;
import ecommerce.reviews.Review;

public class Laptop extends Product {
	
	private String brand;
	private String model;
	private String color;
	private boolean isGamer;
	private boolean hasSsd;
	
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

	public boolean getIsGamer() {
		return isGamer;
	}

	public void setIsGamer(boolean isGamer) {
		this.isGamer = isGamer;
	}

	public boolean getHasSsd() {
		return hasSsd;
	}

	public void setHasSsd(boolean hasSsd) {
		this.hasSsd = hasSsd;
	}

	public Laptop(String category, String brand, String model, String color, boolean isGamer, boolean hasSsd, double price, int quantity) {
		this.setCategory(category);
		this.setPrice(price);
		this.setQuantity(quantity);
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.isGamer = isGamer;
		this.hasSsd = hasSsd;
		this.setProductRef(this.generateRef());
		this.setReviews(new HashMap<User, Review>());
	}
	
	public Laptop(Laptop laptop) {
		this.setProductRef(laptop.getProductRef());
		this.setCategory(laptop.getCategory());
		this.setPrice(laptop.getPrice());
		this.setDiscount(laptop.getDiscount());
		this.setQuantity(laptop.getQuantity());
		this.brand = laptop.getBrand();
		this.model = laptop.getModel();
		this.color = laptop.getColor();
		this.isGamer = laptop.getIsGamer();
		this.hasSsd = laptop.getHasSsd();
		if (laptop.getReviews() == null) {
			this.setReviews(new HashMap<User, Review>());
		}else {
			this.setReviews(laptop.getReviews());
		}
	}
	
	@Override
	public String generateRef() {
		// TODO Auto-generated method stub
		String ref = this.getCategory()+"_"+this.brand+"_"+this.model+"_"+this.color;
		if (this.isGamer) {
			ref+="_"+"Gamer";
		}
		if (this.hasSsd) {
			ref+="_"+"SSD";
		}
		return ref;
	}
	
	@Override
	public String toString(boolean displayQuantity) {
		String isGamer = "No";
		String hasSsd = "No";
		String overallReview = "";
		if (this.getReviews().size() > 0) {
			overallReview = String.valueOf(this.getOverallProductReview()) + " out of 5";
		}else {
			overallReview = "This product doesn't have any review yet";
		}
		if (this.getIsGamer()) {
			isGamer = "Yes";
		}
		if (this.getHasSsd()) {
			hasSsd = "Yes";
		}
		if (displayQuantity) {
			return "Ref -> "+this.getProductRef()+"\t|\tCategory -> "+this.getCategory()+"\t|\tBrand -> "+this.brand+"\t|\tModel -> "+this.model
					+"\t|\tColor -> "+this.color+"\t|\tQuantity -> "+String.valueOf(this.getQuantity())+"\t|\tGamer laptop -> "+isGamer+
					"\t|\tHas SSD card -> "+hasSsd+"\t|\tPrice(unit) -> "+String.valueOf(this.getPrice())+"\t|\tDiscount -> "
					+String.valueOf(this.getDiscount())+"%"+"\t|\tOverall review -> "+overallReview;
		}else {
			return "Ref -> "+this.getProductRef()+"\t|\tCategory -> "+this.getCategory()+"\t|\tBrand -> "+this.brand+"\t|\tModel -> "+this.model
					+"\t|\tColor -> "+this.color+"\t|\tGamer laptop -> "+isGamer+"\t|\tHas SSD card -> "+hasSsd+"\t|\tPrice(unit) -> "
					+String.valueOf(this.getPrice())+"\t|\tDiscount -> "+String.valueOf(this.getDiscount())+"%"+"\t|\tOverall review -> "+overallReview;
		}
	}

}
