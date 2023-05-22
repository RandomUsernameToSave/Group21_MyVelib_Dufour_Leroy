package core;

public interface DockingStation {

	public GPS getGPS();
	public int countFreePlaces() ;
	public boolean hasBikeType(String bikeType);
	public void pickBike(int parkingSlot, User user);
	public void dropBike (int parkingSlot,User user);
}
