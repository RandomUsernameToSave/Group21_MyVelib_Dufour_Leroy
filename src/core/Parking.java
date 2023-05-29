package core;

import java.util.UUID;

/***
 * Parking of a bicycle : used in DockingStations*/
public class Parking {
	private UUID parkingID;
	private String parkingState;// free, occupied, out-of-order est ce que là on ferait pas un enum?
	private Bicycle CurrentBicycle;
	
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
	public boolean isFree() {
		return parkingState == "free";
	}
	public boolean isOccupied() {
		return parkingState == "occupied";
	}
	public boolean isOutOfOrder() {
		return parkingState == "out-of-order"; //ces lignes dépendent de comment on a défini parkingstate avant
	}
	
	public Bicycle getCurrentBicycle() {
		return this.CurrentBicycle;
	}
	public void setCurrentBicycle(Bicycle bike) {
		CurrentBicycle = bike;
		if (bike == null) {
			parkingState = "Free";
		}
		else {
			parkingState = "Occupied";
		}
	}
	
}
