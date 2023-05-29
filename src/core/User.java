package core;

import java.util.UUID;

/**
 * User object : is able to rent and return a bike, calculate cost and add to the user 
 * accordingly*/
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
	
	public String getName() {
		return name;
	}
	public User(String name, UUID creditCard,Cards registrationCard) {
		this.name = name;
		this.creditCard = creditCard;
		this.registrationCard = registrationCard;
		if (!(registrationCard==null) ) {
			registrationCard.setUser(this);
		}
		this.userID = UUID.nameUUIDFromBytes(name.getBytes());
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

	/**
	 * Drop the bike and charge the User with the calculated cost using his registrationCard
	 * The station should have an empty slot. If not the function will not drop the bicycle
	 * at the station.
	 * 
	 * @param station the docking station where the user is returning the bicycle
	 * @param minutes The number of minutes the user has rented the bike
	 * 
	 * Example : 
	 * 
	 * User user = new User("Alice");
	 * DockingStation station = new StandardStation(4, new GPS(0.,0.));
	 * for (Parking park : station) {
				if (Math.random()<=0.7) {
					if (Math.random()<=0.3) {
						park.setCurrentBicycle(bicycleFactory.getBicycle("Electrical") );
					}
					else {
						park.setCurrentBicycle(bicycleFactory.getBicycle("Mechanical") );
					}
				}
	 * user.pickBike(station);
	 * user.returnBike(station,10);
	 * 
	 * Here the user rented the bike for 10 minutes
	 * 
	 * */
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
	
	
	/**
	 * <h1>Rent the bike</h1>
	 * Check if the user is already renting a bike, if not :
	 * Remove the bike from the parking of the docking station and set it to the user.
	 * 
	 * @param station the docking station where the user is renting the bicycle
	 * @param int parkingSlot the parking slot where the user is renting the bike
	 * Example : 
	 * 
	 * User user = new User("Alice");
	 * DockingStation station = new StandardStation(4, new GPS(0.,0.));
	 * for (Parking park : station) {
				if (Math.random()<=0.7) {
					if (Math.random()<=0.3) {
						park.setCurrentBicycle(bicycleFactory.getBicycle("Electrical") );
					}
					else {
						park.setCurrentBicycle(bicycleFactory.getBicycle("Mechanical") );
					}
				}
	 * user.rentingBike(station,0);
	 * 
	 * Here the user rented the bike at the parking number 0.
	 * 
	 * */
	public void rentingBike(DockingStation station, int parkingSlot) {
		// Authentification à faire
		
		if (isRentingBike) {
			System.out.println("Is already renting a bike");
		}
		else {
			station.pickBike(parkingSlot, this);
		}
		
		
	}
	/**
	 * <h1>Rent the bike</h1>
	 * Check if the user is already renting a bike, if not :
	 * Remove the bike from the parking of the docking station and set it to the user.
	 * <h3>Beware !</h3> if the station doesn't have a bicycle of the type asked by the user, the method will do nothing.
	 * 
	 * @param station the docking station where the user is renting the bicycle
	 * @param bikeType The bike type : currently it should be "Mechanical" or "Electrical"
	 * 
	 * Example : 
	 * 
	 * User user = new User("Alice");
	 * DockingStation station = new StandardStation(4, new GPS(0.,0.));
	 * for (Parking park : station) {
				if (Math.random()<=0.7) {
					if (Math.random()<=0.3) {
						park.setCurrentBicycle(bicycleFactory.getBicycle("Electrical") );
					}
					else {
						park.setCurrentBicycle(bicycleFactory.getBicycle("Mechanical") );
					}
				}
	 * user.rentingBike(station, "Mechanical");
	 * 
	 * Here the user rented a "Mechanical" bike.
	 * 
	 * */
	public void rentingBike(DockingStation station, String bikeType) {
	
		
		if (isRentingBike) {
			System.out.println("Is already renting a bike");
		}
		else {
			station.pickBike( this,bikeType);
		}
		
		
	}

	public void returnBike(GPS gPSlocation, int duration) {
		
		this.currentBicycle.setUser(null);
		this.currentBicycle.setGPS(gPSlocation);
		this.currentBicycle.setTime(duration);
	
		totalCharge += (int)1.1*this.currentBicycle.accept(registrationCard);
		
		
	}
	
	
}
