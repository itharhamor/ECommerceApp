package ecommerce.client.input;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import ecommerce.authentication.Authentication;
import ecommerce.authentication.User;
import ecommerce.payment.Transaction;
import ecommerce.reviews.Review;
import ecommerce.shopping.Cart;
import ecommerce.shopping.Order;
import ecommerce.store.Laptop;
import ecommerce.store.Phone;
import ecommerce.store.Product;
import ecommerce.store.Store;

public class ClientInput {
	
	public static Scanner input = new Scanner(System.in);
	
	public ClientInput() {
		
	}
	
	private int readRating() {
		String rating = "";
		int ratingInt = 0;
		System.out.println("\n");
		System.out.print("Rating (1->5) : ");
		while(true) {
			rating = input.nextLine();
			try {
				ratingInt = Integer.parseInt(rating);
				if(ratingInt<=5 && ratingInt>0) {
					break;
				}else {
					System.out.print("Please enter a valid rating (1->5) : ");
				}
			}catch(Exception e) {
				System.out.print("Please enter a valid rating (1->5) : ");
			}
		}
		return ratingInt;
	}
	
	private String readRatingDescription() {
		String description = "";
		System.out.println("\n");
		System.out.print("Description : ");
		while(true) {
			description = input.nextLine();
			if (description.length()>=10) {
				break;
			}
			System.out.print("Description should have at least 10 caracters, please enter a valid description : ");
		}
		return description;
	}
	
	public boolean readPaymentConfirmationStatus() {
		System.out.print("\n Press 'y' to go to checkout otherwise press 'n' to go back to your dashboard : ");
		String decision = "";
		while(true) {
			decision = input.nextLine();
			if(decision.equals("y") || decision.equals("n")) {
				if(decision.equals("n")) {
					return false;
				}
				return true;
			}
			System.out.print("\nPlease enter a valid option ('y' or 'n') : ");
		}
	}
	
	private String readCardHolderName() {
		String name = "";
		System.out.println("\n");
		System.out.print("Name : ");
		while(true) {
			name = input.nextLine();
			if (name.length()>0) {
				break;
			}
			System.out.print("Name can't be empty, please enter a valid name : ");
		}
		return name;
	}
	
	private String readExpirationMonth() {
		String month = "";
		int monthInt = 0;
		System.out.println("\n");
		System.out.print("Expiration month (1->12) : ");
		while(true) {
			month = input.nextLine();
			try {
				monthInt = Integer.parseInt(month);
				if(monthInt<=12 && monthInt>0) {
					break;
				}else {
					System.out.print("Please enter a valid month (1->12) : ");
				}
			}catch(Exception e) {
				System.out.print("Please enter a valid month (1->12) : ");
			}
		}
		return month;
	}
	
	private String readExpirationYear() {
		String year = "";
		int yearInt = 0;
		System.out.println("\n");
		System.out.print("Expiration year : ");
		while(true) {
			year = input.nextLine();
			try {
				yearInt = Integer.parseInt(year);
				if(yearInt >= LocalDate.now().getYear()) {
					break;
				}else {
					System.out.print("Please enter a valid year (must be greater or equal to current year) : ");
				}
			}catch(Exception e) {
				System.out.print("Please enter a valid year (must be greater or equal to current year) : ");
			}
		}
		return year;
	}
	
	private String readCardNumber() {
		String cardNumber = "";
		System.out.println("\n");
		System.out.print("Card number : ");
		while(true) {
			boolean isNumber = true;
			cardNumber = input.nextLine();
			for(int i=0;i<cardNumber.length();i++) {
				try {
					int digit = Integer.parseInt(String.valueOf(cardNumber.charAt(i)));
				}catch(Exception e) {
					isNumber = false;
					break;
				}
			}
			if (isNumber && cardNumber.length() == 16) {
				break;
			}
			System.out.print("Please enter a valid cardNumber (number with 16 digits) : ");
		}
		return cardNumber;
	}

	
	// Laptop_MSI_Katana_white_Gamer_SSD
	private String readProductRef(String validInput) {
		String ref = "";
		System.out.println("\n");
		System.out.print("Reference : ");
		while(true) {
			ref = input.nextLine();
			if (ref.startsWith("Laptop") || ref.startsWith("Phone")) {
				break;
			}
			System.out.print("Please enter a valid reference (exp : "+validInput+") : ");
		}
		return ref;
	}

	private double readPrice() {
		double price;
		System.out.println("\n");
		System.out.print("Price : ");
		while(true) {
			try {
				price = Double.parseDouble(input.nextLine());
				if (price>=1) {
					break;
				}else {
					System.out.print("Please enter a valid price : ");
				}
			}catch(Exception e) {
				System.out.print("Please enter a valid price : ");
			}
		}
		return price;
	}
	private int readDiscount() {

		int discount;
		System.out.println("\n");
		System.out.print("Discount : ");
		while(true) {
			try {
				discount = Integer.parseInt(input.nextLine());
				if (discount>=0 && discount<=90) {
					break;
				}else {
					System.out.print("Please enter a valid discount (0 -> 90) : ");
				}
			}catch(Exception e) {
				System.out.print("Please enter a valid discount (0 -> 90) : ");
			}
		}
		return discount;
	}
	private boolean readHasDiscount()	{
		boolean hasDiscount = false;
		String enteredValue = "";
		System.out.print("Has it a discount ? (press 'y' for yes or 'n' for no) : ");
		while(true) {
			enteredValue = input.nextLine();
			if (enteredValue.equals("y") || enteredValue.equals("n")) {
				if (enteredValue.equals("y")) {
					hasDiscount = true;
				}
				break;
			}else {
				System.out.print("Please choose a valid option (y/n) : ");
			}
		}
		return hasDiscount;
	}
	private int readQuantity() {
		int quantity;
		System.out.println("\n");
		System.out.print("Quantity : ");
		while(true) {
			try {
				quantity = Integer.parseInt(input.nextLine());
				if (quantity>=1) {
					break;
				}else {
					System.out.print("Please enter a valid quantity : ");
				}
			}catch(Exception e) {
				System.out.print("Please enter a valid quantity : ");
			}
		}
		return quantity;
	}
	private String readBrand(String validInput) {
		String brand = "";
		System.out.println("\n");
		System.out.print("Brand : ");
		while(true) {
			brand = input.nextLine();
			if (brand.length()>0) {
				break;
			}
			System.out.print("Please enter a valid brand (exp : "+validInput+") : ");
		}
		return brand;
	}
	private String readModel(String validInput) {
		String model = "";
		System.out.println("\n");
		System.out.print("Model : ");
		while(true) {
			model = input.nextLine();
			if (model.length()>0) {
				break;
			}
			System.out.print("Please enter a valid model (exp : "+validInput+") : ");
		}
		return model;
	}
	private String readCategory(String validInput) {
		String category = "";
		System.out.println("\n");
		System.out.print("Category (Phone/Laptop) : ");
		while(true) {
			category = input.nextLine();
			if (category.equals("Phone") || category.equals("Laptop")) {
				break;
			}
			System.out.print("Please enter a valid model (exp : "+validInput+") : ");
		}
		return category;
	}
	private String readColor() {
		String color = "";
		System.out.println("\n");
		System.out.print("Color : ");
		while(true) {
			color = input.nextLine();
			if (color.length()>0) {
				break;
			}
			System.out.print("Please enter a valid color (exp : white) : ");
		}
		return color;
	}
	private boolean readIsGamer() {
		boolean isGamer = false;
		String enteredValue = "";
		System.out.println("\n");
		System.out.print("Is it a gamer laptop ? (press 'y' for yes or 'n' for no) : ");
		while(true) {
			enteredValue = input.nextLine();
			if (enteredValue.equals("y") || enteredValue.equals("n")) {
				if (enteredValue.equals("y")) {
					isGamer = true;
				}
				break;
			}else {
				System.out.print("Please choose a valid option (y/n) : ");
			}
		}
		return isGamer;
	}
	private boolean readHasSsd() {
		boolean hasSsd = false;
		String enteredValue = "";
		System.out.println("\n");
		System.out.print("Has it an SSD Card ? (press 'y' for yes or 'n' for no) : ");
		while(true) {
			enteredValue = input.nextLine();
			if (enteredValue.equals("y") || enteredValue.equals("n")) {
				if (enteredValue.equals("y")) {
					hasSsd = true;
				}
				break;
			}else {
				System.out.print("Please choose a valid option (y/n) : ");
			}
		}
		return hasSsd;
	}

	public Product readThenCreatePhone() {
		String brand = this.readBrand("HUAWEI");
		String model = this.readModel("P50");
		String color = this.readColor();
		double price = this.readPrice();
		int quantity = this.readQuantity();
		Product phone = new Phone("Phone", brand, model, color, price, quantity);
		System.out.println("\n");
		System.out.println("Product created successfully.");
		phone.addToStore();
		return phone;
	}
	
	public Product readThenCreateLaptop() {
		String brand = this.readBrand("MSI");
		String model = this.readModel("Katana");
		String color = this.readColor();
		boolean isGamer = this.readIsGamer();
		boolean hasSsd = this.readHasSsd();
		double price = this.readPrice();
		int quantity = this.readQuantity();
		Product laptop = new Laptop("Laptop", brand, model, color, isGamer, hasSsd, price, quantity);
		System.out.println("\n");
		System.out.println("Product created successfully.");
		laptop.addToStore();
		return laptop;
	}

	public void readQuantityThenUpdateProduct() {
		Store store = new Store();
		System.out.println("\n");
		System.out.println("Updating product quantity");
		System.out.println("*********************************");
		String ref = this.readProductRef("Laptop_MSI_Katana_white_Gamer_SSD");
		List<Product> product = store.getProductByRef(ref);
		if (product.isEmpty()) {
			store.showSearchResult(product, true);
		}else {
			int quantity = this.readQuantity();
			Product p = product.get(0);
			p.updateProductQuantity(quantity);
			System.out.println("\n");
			System.out.println("Product is updated successfully.");
		}
	}

	public void readPriceThenUpdateProduct() {
		Store store = new Store();
		System.out.println("\n");
		System.out.println("Updating product price");
		System.out.println("*********************************");
		String ref = this.readProductRef("Laptop_MSI_Katana_white_Gamer_SSD");
		List<Product> product = store.getProductByRef(ref);
		if (product.isEmpty()) {
			store.showSearchResult(product, true);
		}else {
			double price = this.readPrice();
			Product p = product.get(0);
			p.updateProductPrice(price);
			System.out.println("\n");
			System.out.println("Product is updated successfully.");
		}
	}

	public void readdiscountThenUpdateProduct() {
		Store store = new Store();
		System.out.println("\n");
		System.out.println("Updating product discount");
		System.out.println("*********************************");
		String ref = this.readProductRef("Laptop_MSI_Katana_white_Gamer_SSD");
		List<Product> product = store.getProductByRef(ref);
		if (product.isEmpty()) {
			store.showSearchResult(product, true);
		}else {
			int discount = this.readDiscount();
			Product p = product.get(0);
			p.updateProductDiscount(discount);
			System.out.println("\n");
			System.out.println("Product is updated successfully.");
		}
	}

	public void readRefThenRemoveProduct() {
		Store store = new Store();
		System.out.println("\n");
		System.out.println("Removing product");
		System.out.println("*********************************");
		String ref = this.readProductRef("Laptop_MSI_Katana_white_Gamer_SSD");
		List<Product> product = store.getProductByRef(ref);
		if (product.isEmpty()) {
			store.showSearchResult(product, true);
		}else {
			Product p = product.get(0);
			p.RemoveProductFromStore();
			System.out.println("\n");
			System.out.println("Product is removed successfully.");
		}
}
	
	public boolean needToReadFilter(String filter) {
		boolean filterIsApplied = false;
		String enteredValue = "";
		System.out.print("Do you want to apply a filter by "+filter+" ? (press 'y' for yes or 'n' for no) : ");
		while(true) {
			enteredValue = input.nextLine();
			if (enteredValue.equals("y") || enteredValue.equals("n")) {
				if (enteredValue.equals("y")) {
					filterIsApplied = true;
				}
				break;
			}else {
				System.out.print("Please choose a valid option (y/n) : ");
			}
		}
		return filterIsApplied;
	}
	
	public void readThenApplyFilters() {
		String category = "";
		boolean hasDiscount = false;
		HashMap<String, Product> queryResult = Store.getProducts();
		Store store = new Store();
		if (this.needToReadFilter("category")) {
			category = this.readCategory("Laptop");
			queryResult = store.applySimpleFilter(category, queryResult);
			if (this.needToReadFilter("discount")) {
				hasDiscount = this.readHasDiscount();
				queryResult = store.applyDiscountFilter(hasDiscount, queryResult);
			}
			if(category.equals("Phone")) {
				this.applyFiltersPhone(queryResult);
			}else {
				this.applyFiltersLaptop(queryResult);
			}
		}else {
			if (this.needToReadFilter("discount")) {
				hasDiscount = this.readHasDiscount();
				queryResult = store.applyDiscountFilter(hasDiscount, queryResult);
			}
		}
		List<Product> query = new ArrayList(queryResult.values());
		if (Authentication.currentSession.getCurrentUser().getIsAdmin()) {
			store.showSearchResult(query, true);
		}else {
			store.showSearchResult(query, false);
		}
	}
	
	public HashMap<String, Product> applyFiltersPhone(HashMap<String, Product> queryResult) {
		String brand = "";
		String model = "";
		Store store = new Store();
		if (this.needToReadFilter("Brand")) {
			brand = this.readBrand("HUAWEI");
			queryResult = store.applySimpleFilter(brand, queryResult);
		}
		if (this.needToReadFilter("model")) {
			model = this.readModel("Galaxy A31");
			queryResult = store.applySimpleFilter(model, queryResult);
		}
		return queryResult;
	}
	
	public HashMap<String, Product> applyFiltersLaptop(HashMap<String, Product> queryResult) {
		String brand = "";
		String model = "";
		boolean isGamer = false;
		boolean hasSsd = false;
		Store store = new Store();
		if (this.needToReadFilter("Brand")) {
			brand = this.readBrand("HUAWEI");
			queryResult = store.applySimpleFilter(brand, queryResult);
		}
		if (this.needToReadFilter("model")) {
			model = this.readModel("Galaxy A31");
			queryResult = store.applySimpleFilter(model, queryResult);
		}
		if (this.needToReadFilter("Gamer laptops")) {
			isGamer = this.readIsGamer();
			queryResult = store.applyFilterIsGamer(isGamer, queryResult);
		}
		if (this.needToReadFilter("laptops having SSD card")) {
			hasSsd = this.readHasSsd();
			queryResult = store.applyFilterHasSsd(hasSsd, queryResult);
		}
		return queryResult;
	}
	
	public void readThenSearchProductByRef() {
		Store store = new Store();
		System.out.println("\n");
		String ref = this.readProductRef("Laptop_MSI_Katana_white_Gamer_SSD");
		List<Product> product = store.getProductByRef(ref);
		if (product.isEmpty()) {
			if (Authentication.currentSession.getCurrentUser().getIsAdmin()) {
				store.showSearchResult(product, true);
			}else {
				store.showSearchResult(product, false);
			}
		}else {
			if (Authentication.currentSession.getCurrentUser().getIsAdmin()) {
				store.showSearchResult(product, true);
			}else {
				store.showSearchResult(product, false);
			}
		}
	}
	
	public Product readRefQuantity() {
		Store store = new Store();
		String ref = this.readProductRef("Laptop_MSI_Katana_white_Gamer_SSD");
		int quantity = this.readQuantity();
		List<Product> product = store.getProductByRef(ref);
		if (product.isEmpty()) {
			return null;
		}else {
			Product p = Store.getProducts().get(ref);
			Laptop l;
			Phone ph;
			Product copyOfProduct;
			if (p instanceof Laptop) {
				l = (Laptop) p;
				copyOfProduct = new Laptop(l);
			}else {
				ph = (Phone) p;
				copyOfProduct = new Phone(ph);
			}
			copyOfProduct.setQuantity(quantity);
			return copyOfProduct;
		}
		
	}
	
	public void readRefThenDeleteItemFromCart() {
		String ref = this.readProductRef("Laptop_MSI_Katana_white_Gamer_SSD");
		Cart c = Authentication.currentSession.getCurrentUser().getShoppingCart();
		if (!c.itemExists(ref)) {
			System.out.println("This product is not present in the shopping cart");
		}else {
			Product p = c.getItems().get(ref);
			if (c.removeItem(p)) {
				System.out.println("Product "+ref+" is removed from your shopping cart");
			}else {
				System.out.println("Problem encountred when trying to remove the product "+ref);
			}
		}
	}
	
	public void readRefQuantityThenUpdateCartItem() {
		String ref = this.readProductRef("Laptop_MSI_Katana_white_Gamer_SSD");
		int quantity = this.readQuantity();
		Cart c = Authentication.currentSession.getCurrentUser().getShoppingCart();
		if (!c.itemExists(ref)) {
			System.out.println("This product is not present in the shopping cart");
		}else {
			Product p = c.getItems().get(ref);
			if (c.updateItemQuantity(ref, quantity)) {
				System.out.println("Product "+ref+" is updated successfully");
			}else {
				System.out.println("Problem encountred when trying to update the product "+ref);
			}
		}
	}
	
	public Transaction readCardInformation(Order order) {
		boolean goToChockout = this.readPaymentConfirmationStatus();
		if (!goToChockout) {
			return null;
		}
		System.out.println("\nIn order to proceed to payment, please enter the following information : ");
		if (order == null) {
			System.out.println("Server problem, retry later");
			return null;
		}
		String cardHolderName = this.readCardHolderName();
		String cardNumber = this.readCardNumber();
		String expirationMonth = this.readExpirationMonth();
		String expirationYear = this.readExpirationYear();
		Transaction tr = new Transaction(cardHolderName, cardNumber, expirationMonth, expirationYear, order.getCart().calculateTotal(), order);
		tr.setAmount(order.getCart().calculateTotal());
		tr.paymentGateway();
		return tr;
	}
	
	public Product readProductThenProceedToReview() {
		Store store = new Store();
		User user = Authentication.currentSession.getCurrentUser();
		String ref = this.readProductRef("Laptop_MSI_Katana_white_Gamer_SSD");
		List<Product> products = store.getProductByRef(ref);
		if (products.isEmpty()) {
			System.out.println("\nProduct with reference "+ref+" doesn't exist in store.");
			return null;
		}
		Product p = products.get(0);
		return p;
	}
	
	public Review readReviewThenAdd() {
		User user = Authentication.currentSession.getCurrentUser();
		Product p = this.readProductThenProceedToReview();
		if (p == null) {
			return null;
		}
		if (!user.hasBought(p)) {
			return null;
		}
		if (p.getReviews().containsKey(user)) {
			System.out.println("\nYou already have rated this product. Try to update your rating instead!");
			return null;
		}
		int rating = this.readRating();
		String description = this.readRatingDescription();
		Review review = new Review(p, user, rating, description);
		p.getReviews().put(user, review);
		System.out.println("\n");
		System.out.println(p.getProductRef()+" : Review added successfully!");
		return review;
	}
	
	public boolean readReviewThenDelete() {
		User user = Authentication.currentSession.getCurrentUser();
		Product p = this.readProductThenProceedToReview();
		if (p == null) {
			return false;
		}
		if (!p.getReviews().containsKey(user)) {
			System.out.println("\nYou didn't rate this product yet!");
			return false;
		}
		p.getReviews().remove(user);
		System.out.println("\n");
		System.out.println(p.getProductRef()+" : Review deleted successfully!");
		return true;
	}
	
	public Review readReviewThenUpdate() {
		User user = Authentication.currentSession.getCurrentUser();
		Product p = this.readProductThenProceedToReview();
		if (p == null) {
			return null;
		}
		if (!p.getReviews().containsKey(user)) {
			System.out.println("\nYou didn't rate this product yet!");
			return null;
		}
		Review review = p.getReviews().get(user);
		int rating = this.readRating();
		String description = this.readRatingDescription();
		review.setRating(rating);
		review.setDescription(description);
		System.out.println("\n");
		System.out.println(p.getProductRef()+" : Review updated successfully!");
		return review;
	}
	
	public void displayReviews() {
		User user = Authentication.currentSession.getCurrentUser();
		Product p = this.readProductThenProceedToReview();
		if (!(p == null)) {
			System.out.println(p.displayReviews());
		}
	}
}
