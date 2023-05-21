package core;

import java.util.UUID;

public class User {

	/**
	 * <h1> Main User Class </h1>
	 * Create User and store informations on it, every User has a unique I.
	 * 
	 * */
	private String name;
	private UUID userID;
	private GPS userGPS;
	private Cards creditCard;
	private Cards registrationCard;
	private int timecreditBalance=0;
	private int totalCharge=0;
	private int nbRides=0;
	
	public User(String name, Cards creditCard,Cards registrationCard, int nbRides) {
		this.name = name;
		this.creditCard = creditCard;
		this.registrationCard = registrationCard;
		this.userID = java.util.UUID.randomUUID();
		this.nbRides = nbRides;
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
	public int getnbRides() {
		return this.nbRides;
	}
	public UUID getuserID() {
		return this.userID;
	}
	
	public void setTimecreditBalance(int minutes) {
		this.timecreditBalance = minutes;
	}
	public void setTotalCharge(int minutes) {
		this.totalCharge = minutes;
	}

	public void returnBike() {
		this.nbRides ++;
	}
	
	
}
