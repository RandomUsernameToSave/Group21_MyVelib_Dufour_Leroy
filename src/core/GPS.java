package core;

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

	
}
