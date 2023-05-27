package test;

import static org.junit.jupiter.api.Assertions.*;

import core.GPS;
import core.MechanicalBicycle;

import java.util.ArrayList;
import java.util.UUID;
import core.CardsFactory;
import core.User;
import core.Bicycle;
import core.BicycleFactory;
import core.DockingStation;
import core.ElectricalBicycle;
import core.Parking;
import core.StandardStation;
import core.plusStation;
import core.Cards;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DockingStationTest {

	@Test
	void CountFreePlaceOfA4parkingFreeStationStandard() {
		DockingStation station = new StandardStation(4,new GPS());
		
		if (station.countFreePlaces()==4) {
			assert(true);
		}
		else {
			assert(false);
		}
	}
	@Test
	void CountFreePlaceOfA4parkingFreeStationPlus() {
		DockingStation station = new plusStation(4, new GPS());
		
		if (station.countFreePlaces()==4) {
			assert(true);
		}
		else {
			assert(false);
		}
	}
	
	@Test
	void DoesTheStationHasElectrical() {
		DockingStation station = new plusStation(4, new GPS());
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
	
	@Test
	void bikePickedIsEqualToTheBikeOfTheStation() {
		BicycleFactory Factory = new BicycleFactory();
		CardsFactory cardFact = new CardsFactory();
		Cards card = cardFact.getCards("Vlibre");
		
		User Thomas = new User("Thomas", UUID.randomUUID(), card);
		
		DockingStation station = new plusStation(4,new GPS());
		ArrayList<Parking> listSlots = station.getListSlots();
		
		Bicycle bike = Factory.getBicycle("Electrical");
		Parking parking0 = listSlots.get(0);
		parking0.setCurrentBicycle(bike);
		
		station.pickBike(0, Thomas);
		
		if (Thomas.getCurrentBicycle() == bike) {
			assert(true);
		}
		else {
			assert(false);
		}
	}
	
	@Test
	void bikePickedLeavesTheParkingEmpty() {
		BicycleFactory Factory = new BicycleFactory();
		CardsFactory cardFact = new CardsFactory();
		Cards card = cardFact.getCards("Vlibre");
		
		User Thomas = new User("Thomas", UUID.randomUUID(), card);
		
		DockingStation station = new plusStation(4, new GPS());
		ArrayList<Parking> listSlots = station.getListSlots();
		
		Bicycle bike = Factory.getBicycle("Electrical");
		Parking parking0 = listSlots.get(0);
		parking0.setCurrentBicycle(bike);
		
		station.pickBike(0, Thomas);
		
		if (parking0.isFree()) {
			assert(true);
		}
		else {
			assert(false);
		}
	}


		@Test
		void
		countFreePlacesReturnsCorrectNumberOfFreePlaces() { // Create a StandardStation object
			// with 5 parking slots
			GPS gps = new GPS(48.8566, 2.3522);
			StandardStation station = new StandardStation(5, gps);

			// Set 2 parking slots as occupied
			station.getListSlots().get(0).setCurrentBicycle(new MechanicalBicycle());
			station.getListSlots().get(1).setCurrentBicycle(new MechanicalBicycle());

			// Assert that countFreePlaces returns 3
			assertEquals(3, station.countFreePlaces());
		}

		@Test
		void countFreePlacesReturnsZeroWhenNoFreePlaces() {
			GPS gps = new GPS(48.8566, 2.3522);
			StandardStation station = new StandardStation(2, gps);

			// occupy all parking slots
			station.getListSlots().get(0).setCurrentBicycle(new MechanicalBicycle());
			station.getListSlots().get(0).changeState("occupied");
			station.getListSlots().get(1).setCurrentBicycle(new MechanicalBicycle());
			station.getListSlots().get(1).changeState("occupied");

			int freePlaces = station.countFreePlaces();

			assertEquals(0, freePlaces);
		}
		

		    @Test
		    @DisplayName("Should return 0 when no electrical bikes are available")
		    void whereBikeTypeWhenNoElectricalBikesAvailable() {
		        GPS gps = new GPS(48.8566, 2.3522);
		        plusStation station = new plusStation(5, gps);

		        int expected = 0;
		        int actual = station.WhereBikeType("Electrical");

		        assertEquals(expected, actual);
		    }

		    @Test
		    @DisplayName("Should return the index of the first parking slot with a mechanical bike")
		    void whereBikeTypeWhenMechanicalBikeExists() { // Create a plusStation object with 3 parking
		                                                      // slots
		        GPS gps = new GPS(48.8584, 2.2945);
		        plusStation station = new plusStation(3, gps);

		        // Add a mechanical bike to the first parking slot
		        MechanicalBicycle bike = new MechanicalBicycle();
		        station.getListSlots().get(0).setCurrentBicycle(bike);

		        // Call the WhereBikeType method with "Mechanical" as argument
		        int result = station.WhereBikeType("Mechanical");

		        // Assert that the result is 0, which is the index of the first parking slot
		        assertEquals(0, result);
		    }

		    @Test
		    @DisplayName("Should return 0 when no mechanical bikes are available")
		    void whereBikeTypeWhenNoMechanicalBikesAvailable() {
		        GPS gps = new GPS(48.8566, 2.3522);
		        plusStation station = new plusStation(2, gps);

		        // Set up the parking slots with electrical bikes
		        station.getListSlots().get(0).setCurrentBicycle(new ElectricalBicycle());
		        station.getListSlots().get(1).setCurrentBicycle(new ElectricalBicycle());

		        // Test the method with a mechanical bike type
		        int result = station.WhereBikeType("Mechanical");

		        // Assert that the result is 0, since there are no mechanical bikes available
		        assertEquals(0, result);
		    }

		    @Test
		    @DisplayName("Should return the index of the first parking slot with an electrical bike")
		    void whereBikeTypeWhenElectricalBikeExists() { // Create a plusStation object with 3 parking
		                                                      // slots
		        GPS gps = new GPS(48.8584, 2.2945);
		        plusStation station = new plusStation(3, gps);

		        // Create an ElectricalBicycle object and set it to the first parking slot
		        ElectricalBicycle electricalBike = new ElectricalBicycle();
		        station.getListSlots().get(0).setCurrentBicycle(electricalBike);

		        // Create a MechanicalBicycle object and set it to the second parking slot
		        MechanicalBicycle mechanicalBike = new MechanicalBicycle();
		        station.getListSlots().get(1).setCurrentBicycle(mechanicalBike);

		        // Test the WhereBikeType method with "Electrical" as parameter
		        int index = station.WhereBikeType("Electrical");
		        assertEquals(0, index);
		    }
	

	

}
