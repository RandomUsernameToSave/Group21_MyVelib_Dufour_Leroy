package core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Plus station implementation : handle picking a bike and returning a bike 
 * Composed of an array of parking*/
public class plusStation implements DockingStation {
	private UUID stationID;
	private boolean onService;
	private GPS stationGPS;
	private ArrayList<Parking> listSlots = new ArrayList<Parking>();
	private int totalNumberRenting=0;
	private int totalNumberDropping=0;
	
	public int getTotalNumberDropping() {
		return totalNumberDropping;
	}
	public void setTotalNumberDropping(int totalNumber) {
		totalNumberDropping=totalNumber;
	}
	public plusStation(int nbreParking, GPS stationGPS) {
		this.stationID = java.util.UUID.randomUUID();
		this.onService = true;
		this.stationGPS = stationGPS;
		for (int i=0; i<nbreParking; ++i){
			this.listSlots.add( new Parking() ); // a voir si on fait pas une classe parking (limite une nested class)
		}
		
		
	}
	public int getTotalNumberRenting() {
		return totalNumberRenting;
	}
	public void setTotalNumberRenting(int totalNumber) {
		totalNumberRenting=totalNumber;
	}
	
	public void setOnService(boolean b) {
		onService = b;
	}
	public UUID getstationID() {
		return this.stationID;
	}
	public GPS getGPS() {
		return this.stationGPS;
	}
	@Override
	public int countFreePlaces() {
        int freeCount = 0;
        for (Parking place : listSlots) {
            if (place.isFree()) {
                freeCount++;
            }
        }
        return freeCount;
}
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
		
		// on recup le velo à la position du parking
		Bicycle bike = parking.getCurrentBicycle();
		bike.setUser(user);
		user.setCurrentBicycle(bike);
		parking.changeState("free");
		user.setIsRentingBike(true);
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
		Bicycle bike = user.getCurrentBicycle();
		
		parking.changeState("occupied");
		parking.setCurrentBicycle(bike);
		user.setCurrentBicycle(null);
		user.setIsRentingBike(false);
		
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
	public ArrayList<Parking> getListSlots() {
		return listSlots;
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
	@Override
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
}
