package ecommerce.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Store {
	private static HashMap<String, Product> products = new HashMap<String, Product>();
	
	public static HashMap<String, Product> getProducts() {
		return products;
	}

	public static void setProducts(HashMap<String, Product> products) {
		Store.products = products;
	}
	
	public Store() {
		
	}
	
	public boolean isQuantitySufficient(Product p) {
		Product prod = products.get(p.getProductRef());
		if (prod == null) {
			return false;
		}
		if (prod.getQuantity() >= p.getQuantity()) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isOutOfStock(Product p) {
		if (p==null) {
			return true;
		}
		Product prod = products.get(p.getProductRef());
		if (prod == null) {
			return true;
		}
		return prod.getQuantity() == 0;
	}
	
	public void showSearchResult(List<Product> products, boolean displayQuantity) {
		System.out.println("\n");
		if (products.isEmpty()) {
			if (Store.getProducts().isEmpty()) {
				System.out.println("We don't have any product in store");
			}else {
				System.out.println("No item in store matches the given criteria");
			}
		}else {
			for (Product p : products) {
				System.out.println(p.toString(displayQuantity));
				System.out.println(new String(new char[1000]).replace('\0', '-'));
			}
		}
	}
	
	public void displayAllProducts(boolean displayQuantity) {
		List<Product> products = this.getAllProducts();
		this.showSearchResult(products, displayQuantity);
	}
	
	public List<Product> getProductByRef(String ref) {
		List<Product> resultQuery = new ArrayList<Product>();
		if (Store.getProducts().containsKey(ref)) {
			Product p = Store.getProducts().get(ref);
			resultQuery.add(p);
		}
		return resultQuery;
	}
	
	public List<Product> getAllProducts(){ 
		return new ArrayList(Store.getProducts().values());
	}
	
	public List<Product> applySimpleFilter(String filter) {
		List<Product> resultQuery = new ArrayList<Product>();
		for (String ref : Store.getProducts().keySet()) {
			if (ref.contains(filter)) {
				Product p = Store.getProducts().get(ref);
				resultQuery.add(p);
			}
		}
		return resultQuery;
	}
	
	public HashMap<String, Product> applySimpleFilter(String filter, HashMap<String, Product> products) {
		if (products.isEmpty()) {
			return products;
		}
		HashMap<String, Product> resultQuery = new HashMap<String, Product>();
		for (String ref : products.keySet()) {
			if (ref.contains(filter)) {
				Product p = products.get(ref);
				resultQuery.put(ref, p);
			}
		}
		return resultQuery;
	}
	
	public HashMap<String, Product> applyDiscountFilter(boolean hasDiscount, HashMap<String, Product> products) {
		if (products.isEmpty()) {
			return products;
		}
		HashMap<String, Product> resultQuery = new HashMap<String, Product>();
		for (String ref : products.keySet()) {
			Product p = products.get(ref);
			if (p.getDiscount()>0) {
				if(hasDiscount) {
					resultQuery.put(ref, p);
				}
			}else {
				if(!hasDiscount) {
					resultQuery.put(ref, p);
				}
			}
		}
		return resultQuery;
	}
	
	public HashMap<String, Product> applyFilterIsGamer(boolean filter, HashMap<String, Product> products) {
		if (products.isEmpty()) {
			return products;
		}
		HashMap<String, Product> resultQuery = new HashMap<String, Product>();
		for (String ref : products.keySet()) {
			Product p = products.get(ref);
			Laptop l = (Laptop) p;
			if (l.getIsGamer() == filter) {
				products.put(ref, p);
			}
		}
		return resultQuery;
	}
	
	public HashMap<String, Product> applyFilterHasSsd(boolean filter, HashMap<String, Product> products) {
		if (products.isEmpty()) {
			return products;
		}
		HashMap<String, Product> resultQuery = new HashMap<String, Product>();
		for (String ref : products.keySet()) {
			Product p = products.get(ref);
			Laptop l = (Laptop) p;
			if (l.getHasSsd() == filter) {
				products.put(ref, p);
			}
		}
		return resultQuery;
	}
	
}
