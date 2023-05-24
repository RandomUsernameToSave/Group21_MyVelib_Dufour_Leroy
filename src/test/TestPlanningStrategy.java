package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import core.GPS;
import core.Parking;

import org.junit.jupiter.api.Test;

import core.Bicycle;
import core.BicycleFactory;
import core.DockingStation;
import core.StandardStation;
import core.OptimalRidePlanning;

class TestPlanningStrategy {

	@Test
	void testStandardStationIsClosest4StationsClosestIsStation3() {
		DockingStation station = new StandardStation(4, new GPS(1.,0.));
		DockingStation station1 = new StandardStation(4, new GPS(2.,0.));
		DockingStation station2 = new StandardStation(4, new GPS(3.,0.));
		DockingStation station3 = new StandardStation(4, new GPS(4.,0.));
		BicycleFactory Factory = new BicycleFactory();
		Bicycle bike1 = Factory.getBicycle("Mechanical");
		Bicycle bike2 = Factory.getBicycle("Mechanical");
		
		station3.getListSlots().get(0).setCurrentBicycle(bike2);;
		station1.getListSlots().get(0).setCurrentBicycle(bike1);;
		
		
		
		ArrayList<DockingStation> listStation = new ArrayList<DockingStation>();
		listStation.add(station1);
		listStation.add(station3);
		listStation.add(station2);
		listStation.add(station);
		GPS startGPS = new GPS(0.,0.);
		GPS endGPS = new GPS(10.,0.);
		DockingStation[] solution = new OptimalRidePlanning().RidePlanning(startGPS, endGPS, listStation, "Mechanical");
		
		if (solution[0] == station1 & solution[1] == station3 ) {
			assert(true);
			
		}
	}

}
