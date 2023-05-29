package core;

import java.util.UUID;
/**
 * GPS object using (lat,long) coordinates calculate the distance accordingly*/
public class GPS {

	private double x;
	private double y;
	
	public GPS() {
		this.x=0;
		this.y=0;

	}
	public GPS(double x,double y) {
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

			double lat1= this.x;
			double lat2 = gps.getX();
			double long1 = this.y;
			double long2 = gps.getY();
			final int R = 6371; // Radius of the earth

			double latDistance = Math.toRadians(lat2 - lat1);
			double lonDistance = Math.toRadians(long2 - long1);
			double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
					+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
					* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
			double distance = R * c * 1000; // convert to meters

			distance = Math.pow(distance, 2);

			return Math.sqrt(distance);
		}

	public static GPS fromString(String GPSstring) {
		// should be of the form (lat,long) NO SPACE IN BETWEEN
		GPSstring=GPSstring.substring( 1, GPSstring.length() - 1 );
		String[] latLong= GPSstring.split(",");
		double[] latLongDouble = {Double.parseDouble(latLong[0]),Double.parseDouble(latLong[1])};
		return new GPS(latLongDouble[0],latLongDouble[1]);
	}
	@Override
	public String toString() {
		return "(lat ="+x+", long ="+y+")";
	}
	
}
