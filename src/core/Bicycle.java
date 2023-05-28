package core;

public interface Bicycle {

	public int accept(Cards visitor);
	public int getTime();
	public void setTime(int minutes);
	public void setUser(User user);
	public GPS getGPS();
	public void setGPS(GPS bgps);
}
