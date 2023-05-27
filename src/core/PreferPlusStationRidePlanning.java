package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PreferPlusStationRidePlanning implements RidePlanningStrategy {

	/**
	 * Return the closest docking station with the bike type asked by the user from the starting point, and the closest station with an 
	 * empty slot from the end point. If the closest plus station is only 10% more far than the closest station, return the closest plus station.
	 * Uses comparator to sort list accordingly.
	 * 
	 * @param startingGPS the GPS position from where the user is starting.
	 * @param endGPS The GPS position where the user want to go.
	 * @param listStation The list of all station of the system.
	 * @param wantedBikeType The type of the bike wanted by the user : should be "Electrical" or "Mechanical".
	 * 
	 * 
	 * Example : 
	 * <pre>{@code
	 * 	DockingStation station = new StandardStation(4, new GPS(1.,0.));
		DockingStation station1 = new StandardStation(4, new GPS(2.,0.));
		BicycleFactory Factory = new BicycleFactory();
		Bicycle bike1 = Factory.getBicycle("Mechanical");
		Bicycle bike2 = Factory.getBicycle("Mechanical");
		
		station.getListSlots().get(0).setCurrentBicycle(bike2);;
		station1.getListSlots().get(0).setCurrentBicycle(bike1);;
		
		
		
		ArrayList<DockingStation> listStation = new ArrayList<DockingStation>();
		listStation.add(station1);

		listStation.add(station);
		GPS startGPS = new GPS(0.,0.);
		GPS endGPS = new GPS(10.,0.);
		DockingStation[] solution = new PreferPlusStationRidePlanning().RidePlanning(startGPS, endGPS, listStation, "Mechanical");
		
	 * }</pre>
	 * 
	 * */
	public DockingStation[] RidePlanning(GPS startingGPS, GPS endGPS, ArrayList<DockingStation> listStation, String wantedBikeType) {
		/**
		 * Return the closest DockingStation from the GPS point 
		 * Create a comparator that compare the position to endGPS
		 * Sort listStation accordingly */

		ArrayList<DockingStation> listPlusStation = new ArrayList<DockingStation>();
		for (DockingStation station : listStation) {
			if(station instanceof plusStation) {
				listPlusStation.add(station);
			}
		}
		DockingStation endStation = null;
		DockingStation endPlusStation = null;
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
		Collections.sort(listPlusStation,endGPS_distance_comparator);
		
		// Return the first station that has free place
		for (DockingStation station : listStation) {
            if (station.countFreePlaces()>0) {
            	endStation = station;
            	break;
            }
        }
		
		for (DockingStation station : listPlusStation) {
            if (station.countFreePlaces()>0) {
            	endPlusStation = station;
            	break;
            }
        }
		double distanceClosestStation = endStation.getGPS().getDistance(endGPS);
        double distanceClosestPlusStation = endPlusStation.getGPS().getDistance(endGPS);
		if (distanceClosestPlusStation<=1.1*distanceClosestStation) {
			endStation = endPlusStation;
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
		
}}
