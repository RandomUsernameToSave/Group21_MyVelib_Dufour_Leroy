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
		this.nboccupied = 0
		
		for (int i=0; i<nbreParking; ++i){
			this.listSlots.add( new Parking() ); 
		}
		
		public int countFreePlaces() {
	        int freeCount = 0;
	        for (listSlots place : places) {//là jsp pk il comprends pas
	            if (place.isFree()) {
	                freeCount++;
	            }
	        }
	        return freeCount;
	        
	        
	        
	}
		public UUID getstationID() {
			return this.stationID;
		}
		public boolean isWorking() {
			return onService;
		}
		
		
		public int countOccupiedPlaces() {
	        int occupiedCount = 0;
	        for (listSlots place : places) {
	            if (place.isOccupied()) {
	                occupiedCount++;
	            }
	        }
	        return occupiedCount;
	    }
		public int countOutOfOrderPlaces() {
	        int OutOfOrder = 0;
	        for (Place place : places) {
	            if (!place.isOccupied()) {
	            	OutOfOrder++;
	            }
	        }
	        return OutOfOrder;
	    }
}

