package core;

import java.util.UUID;

public class User {

	private String name;
	private UUID userID;
	private GPS userGPS;
	private Cards creditCard;
	private Cards registrationCard;
	private int timecreditBalance=0;
	private int totalCharge=0;
	
	public User(String name, Cards creditCard,Cards registrationCard) {
		this.name = name;
		this.creditCard = creditCard;
		this.registrationCard = registrationCard;
		this.userID = java.util.UUID.randomUUID();
		
	}
	public Cards getRegistrationCard() {
		return this.registrationCard;
	}
	public int getTimecreditBalance() {
		return this.timecreditBalance;
	}
	public int gettotalCharge() {
		return this.totalCharge;
	}
	
	public void setTimecreditBalance(int minutes) {
		this.timecreditBalance = minutes;
	}
	public void setTotalCharge(int minutes) {
		this.totalCharge = minutes;
	}

	public void returnBike() {
		
	}
	
	
}
