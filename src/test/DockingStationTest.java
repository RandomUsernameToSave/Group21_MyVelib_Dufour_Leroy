package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import core.Bicycle;
import core.BicycleFactory;
import core.DockingStation;
import core.Parking;
import core.StandardStation;
import core.plusStation;

import org.junit.jupiter.api.Test;

class DockingStationTest {

	@Test
	void CountFreePlaceOfA4parkingFreeStationStandard() {
		DockingStation station = new StandardStation(4);
		
		if (station.countFreePlaces()==4) {
			assert(true);
		}
		else {
			assert(false);
		}
	}
	@Test
	void CountFreePlaceOfA4parkingFreeStationPlus() {
		DockingStation station = new plusStation(4);
		
		if (station.countFreePlaces()==4) {
			assert(true);
		}
		else {
			assert(false);
		}
	}
	
	@Test
	void DoesTheStationHasElectrical() {
		DockingStation station = new plusStation(4);
		ArrayList<Parking> listSlots = station.getListSlots();
		BicycleFactory Factory = new BicycleFactory();
		Bicycle bike = Factory.getBicycle("Electrical");
		Parking parking0 = listSlots.get(0);
		parking0.setCurrentBicycle(bike);
		
		if (station.hasBikeType("Electrical") & !station.hasBikeType("Mechanical")) {
			assert(true);
		}
		else {
			assert(false);
		}
	}

}