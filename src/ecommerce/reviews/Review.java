package ecommerce.reviews;

import ecommerce.authentication.User;
import ecommerce.store.Product;

public class Review {

		private Product product;
		private User user;
		private int rating;
		private String description;
		
		
		public Product getProduct() {
			return product;
		}
		public void setProduct(Product product) {
			this.product = product;
		}
		
		
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		
		
		public int getRating() {
			return rating;
		}
		public void setRating(int rating) {
			this.rating = rating;
		}
		
		
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
		
		public Review(int rating, String description) {
			this.rating = rating;
			this.description = description;
		}
		
		
		public Review(Product product, User user, int rating, String description) {
			this.product = product;
			this.user = user;
			this.rating = rating;
			this.description = description;
		}
		
		@Override
		public String toString() {
			String result = "";
			result+=this.user.getFullName();
			result+="\t Rating -> "+this.rating+"/5";
			result+="\n"+this.description;
			return result;
		}
		
}
