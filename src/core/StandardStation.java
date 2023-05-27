package core;

import java.util.ArrayList;
import java.util.UUID;

public class StandardStation implements DockingStation{
	private UUID stationID;
	private boolean onService;
	private GPS stationGPS;
	private ArrayList<Parking> listSlots = new ArrayList<Parking>();
	private int nbfree;
	private int nboccupied; //est ce que ya des static et tt ici?
	//il veulent aussi que je caractérise son occupation dans la question 4 bizarre
	
	public StandardStation(int nbreParking, GPS stationGPS) {
		this.stationID = java.util.UUID.randomUUID();
		this.onService = true;
		this.nbfree = nbreParking;
		this.nboccupied = 0;
		this.stationGPS = stationGPS;
		
		for (int i=0; i<nbreParking; ++i){
			this.listSlots.add( new Parking() ); }
		}
	/**
	 * <h1>Pick a bike from a station</h1>
	 * Remove the bike from the parking of the parkingSlot of the docking station and set it to the user.
	 * <h3>Beware !</h3> If there is no bike in the parking at parkingSlot, the function will do nothing.
	 * 
	 * @param parkingSlot The number of the parking slot in the station.
	 * @param user The user that is picking a bike.
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
	 * station.pickBike(0,user);
	 * 
	 * Here the user picked the bike at the parking slot 0.
	 * 
	 * */
	
	
	public void pickBike(int parkingSlot, User user) {
		Parking parking = listSlots.get(parkingSlot);
		
		
		Bicycle bike = parking.getCurrentBicycle();
		if (!(bike == null)) {
			bike.setUser(user);
			parking.setCurrentBicycle(null);
			user.setCurrentBicycle(bike);
			parking.changeState("free");
			user.setIsRentingBike(true);
		}
		
	}
	
	/**
	 * <h1>Pick a bike from a station</h1>
	 * Remove the bike from the parking of the type asked by the user of the docking station and set it to the user.
	 * <h3>Beware !</h3> If there is no bike of the bikeType in the station, the function will do nothing.
	 * 
	 * @param bikeType The number of the parking slot in the station.
	 * @param user
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
	 * station.pickBike(user,"Electrical");
	 * 
	 * Here the user picked an electrical bike at the station.
	 * 
	 * */
	
	public void pickBike(User user, String bikeType) {
		if (this.hasBikeType(bikeType)) {
			int parkingSlot = this.WhereBikeType(bikeType);
			Parking parking = listSlots.get(parkingSlot);
			
			Bicycle bike = parking.getCurrentBicycle();
			if (!(bike == null)) {
				bike.setUser(user);
				parking.setCurrentBicycle(null);
				user.setCurrentBicycle(bike);
				parking.changeState("free");
				user.setIsRentingBike(true);
			}
		}
		
		
	}
	
	public ArrayList<Parking> getListSlots() {
		return listSlots;
	}
	
	/**
	 * <h1>Drop a bike at a station</h1>
	 * Automatically find an empty place to drop the bike, set the bike in the first free parking,
	 * remove the bike from the user.
	 * <h3>Beware !</h3> If the parking is not empty, the function will do nothing.
	 * 
	 * @param parkingSlot The number of the parking slot in the station.
	 * @param user The user that is dropping the bike.
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
	 * station.pickBike(0,user);
	 * station.dropBike(0,user);
	 * Here the user dropped the bike at the parkingSlot 0 of the station.
	 * 
	 * */
	
	public void dropBike (int parkingSlot,User user) {
		// check if parkingSlot is empty
		Parking parking = listSlots.get(parkingSlot);
		if (parking.isFree()) {
			Bicycle bike = user.getCurrentBicycle();
			
			parking.changeState("occupied");
			parking.setCurrentBicycle(bike);
			user.setCurrentBicycle(null);
			user.setIsRentingBike(false);
		}
		
		
	}
	
	/**
	 * <h1>Drop a bike at a station</h1>
	 * Automatically find an empty place to drop the bike, set the bike in the first free parking,
	 * remove the bike from the user.
	 * <h3>Beware !</h3> If the parking is full, the function will do nothing.
	 * 
	 * @param user The user that is dropping the bike.
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
	 * station.pickBike(user, "Mechanical");
	 * station.dropBike(user);
	 * Here the user dropped the bike at the parkingSlot 0 of the station.
	 * 
	 * */
	
	
	public void dropBike (User user) {
		
		if (this.countFreePlaces()>0) {
			Parking parking = this.getFreeParking();
			Bicycle bike = user.getCurrentBicycle();
			
			parking.changeState("occupied");
			parking.setCurrentBicycle(bike);
			user.setCurrentBicycle(null);
			user.setIsRentingBike(false);
		}
		
		
	}
	
	
		public GPS getGPS() {
			return this.stationGPS;
		}
	
		/**
		 * <h1>Count the number of free places in a station</h1>
		 * 
		 * Return the number of freePlaces in the station
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
		 * station.countFreePlaces();
		 * 
		 * 
		 * */
		public int countFreePlaces() {
	        int freeCount = 0;
	        for (Parking place : listSlots) {
	            if (place.isFree()) {
	                freeCount++;
	            }
	        }
	        return freeCount;
		}
		
		/**
		 * <h1>Does the station have the biketype asked by the user ?</h1>
		 * 
		 * Return true if the station has the biketype asked by the user, else return false
		 * 
		 * @param bikeType if the station has this biketype : currently should be "Electrical" or "Mechanical"
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
		 * station.hasBikeType("Mechanical");
		 * 
		 * 
		 * */
		public boolean hasBikeType(String bikeType) {
	        
	        for (Parking place : listSlots) {
	        	if (bikeType == "Electrical") {
	        		if (place.getCurrentBicycle() instanceof ElectricalBicycle) {
		                return true;
		            }
	        	}
	        	if (bikeType == "Mechanical") {
	        		if (place.getCurrentBicycle() instanceof MechanicalBicycle) {
		                return true;
		            }
	        	}
	            
	        }
	        return false;
		}
		
		/**
		 * <h1>Return the index of the parking where the biketype is in the station</h1>
		 * 
		 * Return the index of the parking where the biketype is in the station.
		 * Return 0 if the bikeType is not in the station.
		 * 
		 * @param bikeType if the station has this biketype : currently should be "Electrical" or "Mechanical"
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
		 * station.WhereBikeType("Mechanical");
		 * 
		 * 
		 * */
		public int WhereBikeType(String bikeType) {
	        
	        for (Parking place : listSlots) {
	        	if (bikeType == "Electrical") {
	        		if (place.getCurrentBicycle() instanceof ElectricalBicycle) {
		                return listSlots.indexOf(place);
		            }
	        	}
	        	if (bikeType == "Mechanical") {
	        		if (place.getCurrentBicycle() instanceof MechanicalBicycle) {
	        			return listSlots.indexOf(place);
		            }
	        	}
	            
	        }
	        return 0;
		}
		
		
		public UUID getstationID() {
			return this.stationID;
		}
		public boolean isWorking() {
			return onService;
		}
		
		
		public int countOccupiedPlaces() {
	        int occupiedCount = 0;
	        for (Parking place : listSlots) {
	            if (place.isOccupied()) {
	                occupiedCount++;
	            }
	        }
	        return occupiedCount;
	    }
		public int countOutOfOrderPlaces() {
	        int OutOfOrder = 0;
	        for (Parking place : listSlots) {
	            if (!place.isOccupied()) {
	            	OutOfOrder++;
	            }
	        }
	        return OutOfOrder;
	    }
		
		/**
		 * <h1>Get the first free parking of the station</h1>
		 * Return the first free parking of the station, if there is no free parking return null.
		 * 

		 * Example : 

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
		 * station.getFreeParking();

		 * 
		 * */
		public Parking getFreeParking() {
			for (Parking park : listSlots) {
				if(park.isFree()) {
					return park;
				}
			}
			return null;
		}
}

