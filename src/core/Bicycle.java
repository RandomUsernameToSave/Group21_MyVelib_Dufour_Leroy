package core;

/**
 * Bicycle interface implementation : 2 types : Electrical/Mechanical*/
public interface Bicycle {

	public int accept(Cards visitor);
	public int getTime();
	public void setTime(int minutes);
	public void setUser(User user);
	public GPS getGPS();
	public void setGPS(GPS bgps);
}
