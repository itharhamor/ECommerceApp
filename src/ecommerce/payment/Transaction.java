package ecommerce.payment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ecommerce.shopping.Order;

public class Transaction {
	
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");

	private LocalDateTime createdAt;
	private String cardHolderName;
	private String cardNumber;
	private String expirationMonth;
	private String expirationYear;
	private double amount;
	private boolean isSuccess;
	private Order order;
	
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getExpirationMonth() {
		return expirationMonth;
	}
	public void setExpirationMonth(String expirationMonth) {
		this.expirationMonth = expirationMonth;
	}
	
	public String getExpirationYear() {
		return expirationYear;
	}
	public void setExpirationYear(String expirationYear) {
		this.expirationYear = expirationYear;
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Transaction(String cardHolderName, String cardNumber, String expirationMonth, String expirationYear,
			double amount, Order order) {
		this.cardHolderName = cardHolderName;
		this.cardNumber = cardNumber;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
		this.amount = amount;
		this.order = order;
		this.createdAt = LocalDateTime.now();
	}
	
	public Transaction(Transaction tr) {
		this.cardHolderName = tr.cardHolderName;
		this.cardNumber = tr.cardNumber;
		this.expirationMonth = tr.expirationMonth;
		this.expirationYear = tr.expirationYear;
		this.amount = tr.amount;
		this.order = new Order(tr.order);
		this.createdAt = LocalDateTime.now();
	}
	
	
	public void paymentGateway() {
		this.isSuccess = Math.random() < 0.9; //90% success rate
	}
	
	public String toString() {
		String result="\n";
		result+="Transaction created at "+this.createdAt.format(formatter)+" : \n\n";
		result+=this.order.toString();
		if (this.isSuccess) {
			result+="\n\n -------------> Transaction succeed :)";
		}
		else {
			result+="\n\n -------------> Transaction failed due to network problem. Please retry later. ";
		}
		return result;
	}
	
	
}
