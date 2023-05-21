package core;

import java.util.UUID;

public class ElectricalBicycle implements Bicycle {
	private UUID bicycleID;
	private GPS bicycleGPS;	
	private int time_bike_minutes;
	private User user_renting_currently;
	
	public int getTime() {
		return time_bike_minutes;
	}
	
	public User getUser() {
		return user_renting_currently;
	}
	
	public int accept(BikeVisitor visitor) {
		return visitor.visit(this);
	}
}
