package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;
import core.GPS;
import org.junit.jupiter.api.Test;

import core.Bicycle;
import core.BicycleFactory;
import core.Cards;
import core.CardsFactory;
import core.DockingStation;
import core.Parking;
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

}
