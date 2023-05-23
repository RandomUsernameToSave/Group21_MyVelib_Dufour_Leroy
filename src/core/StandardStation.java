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
	
	public StandardStation(int nbreParking) {
		this.stationID = java.util.UUID.randomUUID();
		this.onService = true;
		this.nbfree = nbreParking;
		this.nboccupied = 0;
		
		for (int i=0; i<nbreParking; ++i){
			this.listSlots.add( new Parking() ); }
		}
		
	public void pickBike(int parkingSlot, User user) {
		Parking parking = listSlots.get(parkingSlot);
		
		// on recup le velo à la position du parking
		Bicycle bike = parking.getCurrentBicycle();
		
		parking.setCurrentBicycle(null);
		user.setCurrentBicycle(bike);
		parking.changeState("free");
		user.setIsRentingBike(true);
	}
	
	public ArrayList<Parking> getListSlots() {
		return listSlots;
	}
	
	public void dropBike (int parkingSlot,User user) {
		// check if parkingSlot is empty
		Parking parking = listSlots.get(parkingSlot);
		Bicycle bike = user.getCurrentBicycle();
		
		parking.changeState("occupied");
		parking.setCurrentBicycle(bike);
		user.setCurrentBicycle(null);
		user.setIsRentingBike(false);
		
	}
	
	
		public GPS getGPS() {
			return this.stationGPS;
		}
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
}

