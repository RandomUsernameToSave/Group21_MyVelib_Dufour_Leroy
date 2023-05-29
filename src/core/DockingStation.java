package core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;
/**
 * Docking station Interface : 2 types : plus Station and standard station
 * */
public interface DockingStation {

	public GPS getGPS();
	public UUID getstationID();
	public int countFreePlaces() ;
	public boolean hasBikeType(String bikeType);
	public void pickBike(int parkingSlot, User user);
	public void dropBike (int parkingSlot,User user);
	public ArrayList<Parking> getListSlots() ;
	public void dropBike (User user);
	public void pickBike(User user, String bikeType);
	public boolean isWorking();
	public int countOccupiedPlaces();
	public int countOutOfOrderPlaces();
	public void setOnService(boolean b);
	public int getTotalNumberRenting();
	public void setTotalNumberRenting(int totalNumber);
	public int getTotalNumberDropping();
	public void setTotalNumberDropping(int totalNumber);
	
}
