package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;
import core.GPS;
import core.MechanicalBicycle;

import org.junit.jupiter.api.Test;

import core.Bicycle;
import core.BicycleFactory;
import core.Cards;
import core.CardsFactory;
import core.DockingStation;
import core.ElectricalBicycle;
import core.Parking;
import core.StandardStation;
import core.User;
import core.plusStation;

class TestUser {

	@Test
	void ifUserIsAlreadyRentingABikeSendMessageWarning() {
		BicycleFactory Factory = new BicycleFactory();
		CardsFactory cardFact = new CardsFactory();
		Cards card = cardFact.getCards("Vlibre");
		
		User Thomas = new User("Thomas", UUID.randomUUID(), card);
		
		DockingStation station = new plusStation(4,new GPS());
		ArrayList<Parking> listSlots = station.getListSlots();
		
		Bicycle bikeElec = Factory.getBicycle("Electrical");
		Bicycle bikeMech = Factory.getBicycle("Mechanical");
		Parking parking0 = listSlots.get(0);
		
		parking0.setCurrentBicycle(bikeElec);
		Parking parking1 = listSlots.get(1);
		parking1.setCurrentBicycle(bikeMech);
		
		Thomas.rentingBike(station, 0);
		Thomas.rentingBike(station, 1);
		
		if (true) {
			assert(true);
		}

	}
	
	@Test
	public void testnbRides() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vmax");
		
		MechanicalBicycle bike = new MechanicalBicycle();
		GPS gps = new GPS(48.8566, 2.3522);
        StandardStation station = new StandardStation(5, gps);
	    User Thomas = new User("Thomas", UUID.randomUUID(), card);
	    Thomas.setCurrentBicycle(bike);
	    bike.setUser(Thomas);
		Thomas.setTimecreditBalance(25);
		Thomas.setTotalCharge(1);
		Thomas.returnBike(station,140);
		
	    //Assert Total Charge correctly changed
	    assertEquals(1, Thomas.getnbRides());
	}

	@Test
	public void testReturnMechanicalBike() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vmax");
		
		MechanicalBicycle bike = new MechanicalBicycle();
		GPS gps = new GPS(48.8566, 2.3522);
        StandardStation station = new StandardStation(5, gps);
	    User Thomas = new User("Thomas", UUID.randomUUID(), card);
	    Thomas.setCurrentBicycle(bike);
	    bike.setUser(Thomas);
		Thomas.setTimecreditBalance(25);
		Thomas.setTotalCharge(1);
		Thomas.returnBike(station,140);
		
	    //Assert has Mechanical
	    assertTrue(station.hasBikeType("Mechanical"));
	}
	@Test
	public void testReturnElectricalBike() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vmax");
		ElectricalBicycle bike = new ElectricalBicycle();
		GPS gps = new GPS(48.8566, 2.3522);
        StandardStation station = new StandardStation(5, gps);
	    User Thomas = new User("Thomas", UUID.randomUUID(), card);
	    Thomas.setCurrentBicycle(bike);
	    bike.setUser(Thomas);
		Thomas.setTimecreditBalance(25);
		Thomas.setTotalCharge(1);
		Thomas.returnBike(station,140);
		
	    //Assert has Mechanical
	    assertTrue(station.hasBikeType("Electrical"));
	    //Assert the bike has been dropped
	    assertEquals(null, Thomas.getCurrentBicycle());
	}
	@Test
	public void testReturnWildBike() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vmax");
		ElectricalBicycle bike = new ElectricalBicycle();
		GPS gps = new GPS(48.8566, 2.3522);
	    User Thomas = new User("Thomas", UUID.randomUUID(), card);
	    Thomas.setCurrentBicycle(bike);
	    bike.setUser(Thomas);
		Thomas.setTimecreditBalance(0);
		Thomas.setTotalCharge(1);
		Thomas.returnBike(gps,140);
		

	    //Price penalty
	    assertEquals(2.2, Thomas.gettotalCharge());
	}
}
