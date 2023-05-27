package core;

import java.util.UUID;

public class ElectricalBicycle implements Bicycle {
	private UUID bicycleID;
	private GPS bicycleGPS;	
	private int time_bike_minutes;
	private User user_renting_currently;
	
	public void setUser(User user) {
		this.user_renting_currently = user;
	}
	
	public void setTime(int minutes) {
		this.time_bike_minutes = minutes;
	}
	public int getTime() {
		return time_bike_minutes;
	}
	
	public User getUser() {
		return user_renting_currently;
	}
	
	public int accept(Cards visitor) {
		return visitor.visit(this);
	}
	public GPS getGPS() {
		return bicycleGPS;
	}
	public GPS setGPS() {
		return bicycleGPS;
	}

}
