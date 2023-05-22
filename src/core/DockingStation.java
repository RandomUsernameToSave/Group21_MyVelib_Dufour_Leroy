package core;

public interface DockingStation {

	public GPS getGPS();
	public int countFreePlaces() ;
	public boolean hasBikeType(String bikeType);
}
