package core;

import java.util.ArrayList;

public interface RidePlanningStrategy {

	public DockingStation RidePlanning(GPS startingGPS, GPS endGPS,ArrayList<DockingStation> listStation);
}
