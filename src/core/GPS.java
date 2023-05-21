package core;

public class GPS {

	private double x;
	private double y;
	
	GPS() {
		this.x=0;
		this.y=0;

	}
	GPS(double x,double y, double z) {
		this.x=x;
		this.y=y;

	}
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}

	public void setGPS(double x,double y) {
		this.x=x;
		this.y=y;
	}
	
	public double getDistance(GPS gps) {
		double q= Math.pow(gps.getX()-this.x,2)+Math.pow(gps.getY()-this.y,2);
		return Math.sqrt(q);
	}
	
}
