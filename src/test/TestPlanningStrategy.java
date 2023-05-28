package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import core.GPS;
import core.Parking;
import core.PreferPlusStationRidePlanning;

import org.junit.jupiter.api.Test;

import core.AvoidPlusStationRidePlanning;
import core.Bicycle;
import core.BicycleFactory;
import core.DockingStation;
import core.StandardStation;
import core.plusStation;
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
		
		if (solution[0].equals(station1) && solution[1].equals(station3)) {
		    assert true;
		}
		
		
	}
	
	@Test
	void testPreferPlusStationInf() {
		DockingStation station = new StandardStation(4, new GPS(1.,0.));
		DockingStation station1 = new StandardStation(4, new GPS(2.,0.));
		DockingStation station2 = new plusStation(4, new GPS(2.,0.));
		DockingStation station3 = new StandardStation(4, new GPS(4.,0.));
		BicycleFactory Factory = new BicycleFactory();
		Bicycle bike1 = Factory.getBicycle("Mechanical");
		Bicycle bike2 = Factory.getBicycle("Mechanical");
		Bicycle bike3 = Factory.getBicycle("Mechanical");
		
		station3.getListSlots().get(0).setCurrentBicycle(bike2);;
		station1.getListSlots().get(0).setCurrentBicycle(bike1);;
		station2.getListSlots().get(0).setCurrentBicycle(bike3);;
		
		
		ArrayList<DockingStation> listStation = new ArrayList<DockingStation>();
		listStation.add(station1);
		listStation.add(station3);
		listStation.add(station2);
		listStation.add(station);
		GPS startGPS = new GPS(0.,0.);
		GPS endGPS = new GPS(10.,0.);
		DockingStation[] solution = new PreferPlusStationRidePlanning().RidePlanning(startGPS, endGPS, listStation, "Mechanical");
		
		if (solution[0].equals(station2) && solution[1].equals(station3)) {
		    assert true;
			
		}
	}
	
	@Test
	void testPreferPlusStationSup() {
		DockingStation station = new StandardStation(4, new GPS(1.,0.));
		DockingStation station1 = new plusStation(4, new GPS(1.,0.));
		DockingStation station2 = new StandardStation(4, new GPS(1.,0.));
		DockingStation station3 = new StandardStation(4, new GPS(4.,0.));
		BicycleFactory Factory = new BicycleFactory();
		Bicycle bike1 = Factory.getBicycle("Mechanical");
		Bicycle bike2 = Factory.getBicycle("Mechanical");
		Bicycle bike3 = Factory.getBicycle("Mechanical");
		
		station3.getListSlots().get(0).setCurrentBicycle(bike2);;
		station1.getListSlots().get(0).setCurrentBicycle(bike1);;
		station2.getListSlots().get(0).setCurrentBicycle(bike3);;
		
		
		ArrayList<DockingStation> listStation = new ArrayList<DockingStation>();
		listStation.add(station1);
		listStation.add(station3);
		listStation.add(station2);
		listStation.add(station);
		GPS startGPS = new GPS(0.,0.);
		GPS endGPS = new GPS(10.,0.);
		DockingStation[] solution = new PreferPlusStationRidePlanning().RidePlanning(startGPS, endGPS, listStation, "Mechanical");
		
		if (solution[0].equals(station1) && solution[1].equals(station3)) {
		    assert true;
		}
	}
		
		
	@Test
	void testNoDesiredBikeAvailable() {
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
		DockingStation[] solution = new OptimalRidePlanning().RidePlanning(startGPS, endGPS, listStation, "Electrical");
		
		assertNull(solution[0]);
	    assertNull(solution[1]);
			
		}
	@Test
	void testNoStationAvailable() {
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
		GPS startGPS = new GPS(10.,0.);
		GPS endGPS = new GPS(20.,0.);
		DockingStation[] solution = new OptimalRidePlanning().RidePlanning(startGPS, endGPS, listStation, "Electrical");
		
		assertNull(solution[0]);
	    assertNull(solution[1]);
			
		}
	@Test
	void testNoStationUtile() {
		DockingStation station = new StandardStation(4, new GPS(1.,0.));
		DockingStation station1 = new StandardStation(4, new GPS(0.,0.));
		DockingStation station2 = new StandardStation(4, new GPS(20.,0.));
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
		GPS startGPS = new GPS(10.,0.);
		GPS endGPS = new GPS(20.,0.);
		DockingStation[] solution = new OptimalRidePlanning().RidePlanning(startGPS, endGPS, listStation, "Electrical");
		
		assertNull(solution[0]);
	    assertNull(solution[1]);
			
		}
	@Test
	void testAvoidPlusStationInf() {
		DockingStation station = new StandardStation(4, new GPS(1.,0.));
		DockingStation station1 = new StandardStation(4, new GPS(2.,0.));
		DockingStation station2 = new plusStation(4, new GPS(2.,0.));
		DockingStation station3 = new StandardStation(4, new GPS(4.,0.));
		BicycleFactory Factory = new BicycleFactory();
		Bicycle bike1 = Factory.getBicycle("Mechanical");
		Bicycle bike2 = Factory.getBicycle("Mechanical");
		Bicycle bike3 = Factory.getBicycle("Mechanical");
		
		station3.getListSlots().get(0).setCurrentBicycle(bike2);;
		station1.getListSlots().get(0).setCurrentBicycle(bike1);;
		station2.getListSlots().get(0).setCurrentBicycle(bike3);;
		
		
		ArrayList<DockingStation> listStation = new ArrayList<DockingStation>();
		listStation.add(station1);
		listStation.add(station3);
		listStation.add(station2);
		listStation.add(station);
		GPS startGPS = new GPS(0.,0.);
		GPS endGPS = new GPS(10.,0.);
		DockingStation[] solution = new AvoidPlusStationRidePlanning().RidePlanning(startGPS, endGPS, listStation, "Mechanical");
		
		if (solution[0].equals(station1) && solution[1].equals(station3)) {
		    assert true;
			
		}
	}
	
	@Test
	void testAvoidPlusStationSup() {
		DockingStation station = new StandardStation(4, new GPS(1.,0.));
		DockingStation station1 = new plusStation(4, new GPS(1.,0.));
		DockingStation station2 = new StandardStation(4, new GPS(1.,0.));
		DockingStation station3 = new StandardStation(4, new GPS(4.,0.));
		BicycleFactory Factory = new BicycleFactory();
		Bicycle bike1 = Factory.getBicycle("Mechanical");
		Bicycle bike2 = Factory.getBicycle("Mechanical");
		Bicycle bike3 = Factory.getBicycle("Mechanical");
		
		station3.getListSlots().get(0).setCurrentBicycle(bike2);;
		station1.getListSlots().get(0).setCurrentBicycle(bike1);;
		station2.getListSlots().get(0).setCurrentBicycle(bike3);;
		
		
		ArrayList<DockingStation> listStation = new ArrayList<DockingStation>();
		listStation.add(station1);
		listStation.add(station3);
		listStation.add(station2);
		listStation.add(station);
		GPS startGPS = new GPS(0.,0.);
		GPS endGPS = new GPS(10.,0.);
		DockingStation[] solution = new PreferPlusStationRidePlanning().RidePlanning(startGPS, endGPS, listStation, "Mechanical");
		
		if (solution[0].equals(station2) && solution[1].equals(station3)) {
		    assert true;
		}
	}
}
