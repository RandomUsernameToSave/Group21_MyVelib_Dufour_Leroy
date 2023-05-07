package core;

import java.util.UUID;

public class User {

	private String name;
	private UUID userID;
	private int GPS;
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
	

	public void returnBike() {
		
	}
	
	
}
