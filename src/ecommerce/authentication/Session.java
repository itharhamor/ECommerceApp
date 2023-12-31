package ecommerce.authentication;

import java.time.LocalTime;

import ecommerce.shopping.Cart;

public class Session {
	
	private boolean isActive;
	private LocalTime createdAt;
	private User currentUser;
	
	public boolean isActive() {
		return isActive;
	}
	public Session(User currentUser, LocalTime createdAt) {
		this.isActive = true;
		this.createdAt = createdAt;
		this.currentUser = currentUser;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public LocalTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalTime createdAt) {
		this.createdAt = createdAt;
	}
	public User getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	public void KillSession() {
		this.isActive = false;
		this.createdAt = null;
		this.currentUser = new User();
		this.currentUser.setOnline(false);
	}
	
	public void initUserCart() {
		this.currentUser.setShoppingCart(new Cart(this.currentUser));
	}

}
