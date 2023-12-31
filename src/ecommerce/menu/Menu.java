package ecommerce.menu;

public interface Menu {
	public void displayMenu();
	public int validateOption();
	public void performAction(int option);
}
