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
	private UUID creditCard; // on va la représenter comme ça pas la peine de coder une banque
	private Cards registrationCard;
	private int timecreditBalance=0;
	private int totalCharge=0;
	private int nbRides=0;
	private boolean isRentingBike = false;
	private Bicycle currentBicycle = null;
	
	public User(String name, UUID creditCard,Cards registrationCard) {
		this.name = name;
		this.creditCard = creditCard;
		this.registrationCard = registrationCard;
		if (!(registrationCard==null) ) {
			registrationCard.setUser(this);
		}
		this.userID = java.util.UUID.randomUUID();
	}
	
	public Bicycle getCurrentBicycle() {
		return currentBicycle;
	}
	
	public void setCurrentBicycle(Bicycle bike) {
		currentBicycle = bike;
	}
	public void setIsRentingBike(boolean b) {
		this.isRentingBike = b;
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

	public void returnBike(DockingStation station, int minutes) {
		this.nbRides ++;
		
		
		// calculate cost
		Bicycle bike = this.currentBicycle;
		

		if (! (bike==null)) {
			bike.setTime(minutes);
			double price = bike.accept(registrationCard);

			totalCharge += price; // pay for the price
			
			station.dropBike( this);
		}

		
		
		
	}
	
	
	
	public void rentingBike(DockingStation station, int parkingSlot) {
		// Authentification à faire
		
		if (isRentingBike) {
			System.out.println("Is already renting a bike");
		}
		else {
			station.pickBike(parkingSlot, this);
		}
		
		
	}
	public void rentingBike(DockingStation station, String bikeType) {
		// Authentification à faire
		
		if (isRentingBike) {
			System.out.println("Is already renting a bike");
		}
		else {
			station.pickBike( this,bikeType);
		}
		
		
	}
	
	
}
