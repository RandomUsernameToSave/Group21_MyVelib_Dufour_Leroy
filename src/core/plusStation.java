package core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
//il me reste à mettre ce que j'ai ajouté dans standard ici mais en vrai je pense qu'on peut le définir comme juste un paramtre différent de la classe station


public class plusStation implements DockingStation {
	private UUID stationID;
	private boolean onService;
	private GPS stationGPS;
	private ArrayList<Parking> listSlots = new ArrayList<Parking>();
	
	public plusStation(int nbreParking, GPS stationGPS) {
		this.stationID = java.util.UUID.randomUUID();
		this.onService = true;
		this.stationGPS = stationGPS;
		for (int i=0; i<nbreParking; ++i){
			this.listSlots.add( new Parking() ); // a voir si on fait pas une classe parking (limite une nested class)
		}
		
		
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
	public void pickBike(int parkingSlot, User user) {
		Parking parking = listSlots.get(parkingSlot);
		
		// on recup le velo à la position du parking
		Bicycle bike = parking.getCurrentBicycle();
		bike.setUser(user);
		user.setCurrentBicycle(bike);
		parking.changeState("free");
		user.setIsRentingBike(true);
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
	public Parking getFreeParking() {
		for (Parking park : listSlots) {
			if(park.isFree()) {
				return park;
			}
		}
		return null;
	}
	
}
