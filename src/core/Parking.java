package core;

import java.util.UUID;

public class Parking {
	private UUID parkingID;
	private String parkingState;// free, occupied, out-of-order
	
	public Parking () {
		this.parkingID = java.util.UUID.randomUUID();
		this.parkingState= "free"  ;
	}
	public Parking (String parkingState) {
		this.parkingID = java.util.UUID.randomUUID();
		this.parkingState= parkingState  ;
	}
	
	public void changeState(String parkingState) {
		this.parkingState= parkingState  ;
	}
	public String getParkingState() {
		return parkingState;
	}
}
