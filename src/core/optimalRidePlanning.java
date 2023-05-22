package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class optimalRidePlanning {
	
	
	
	public DockingStation[] RidePlanning(GPS startingGPS, GPS endGPS, ArrayList<DockingStation> listStation, String wantedBikeType) {
		/**
		 * Return the closest DockingStation from the GPS point 
		 * Create a comparator that compare the position to endGPS
		 * Sort listStation accordingly */

		DockingStation endStation = null;
		DockingStation startingStation = null;
		
		Comparator<DockingStation> endGPS_distance_comparator = new Comparator<DockingStation>() {
			
		    public int compare(DockingStation DS1, DockingStation DS2) {
		        GPS DS1GPS = DS1.getGPS();
		        GPS DS2GPS = DS2.getGPS();
		        
		        double distance1_endGPS = DS1GPS.getDistance(endGPS);
		        double distance2_endGPS = DS2GPS.getDistance(endGPS);
		        
		        
		        return Double.compare(distance1_endGPS, distance2_endGPS);
		    }
		};
		Comparator<DockingStation> startGPS_distance_comparator = new Comparator<DockingStation>() {
			
		    public int compare(DockingStation DS1, DockingStation DS2) {
		        GPS DS1GPS = DS1.getGPS();
		        GPS DS2GPS = DS2.getGPS();
		        
		        double distance1_endGPS = DS1GPS.getDistance(startingGPS);
		        double distance2_endGPS = DS2GPS.getDistance(startingGPS);
		        
		        
		        return Double.compare(distance1_endGPS, distance2_endGPS);
		    }
		};
		
		
		Collections.sort(listStation,endGPS_distance_comparator);
		
		// Return the first station that has free place
		for (DockingStation station : listStation) {
            if (station.countFreePlaces()>0) {
            	endStation = station;
            	break;
            }
        }
		
		Collections.sort(listStation,startGPS_distance_comparator);
		
		// Return the first station that has desired bike type
		for (DockingStation station : listStation) {
            if (station.hasBikeType(wantedBikeType)) {
            	startingStation = station;
            	break;
            }
        }
		
		
		
		return new DockingStation[] {endStation,startingStation};
		
		
	}
}
