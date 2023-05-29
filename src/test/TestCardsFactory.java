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



import java.util.UUID;

import org.junit.jupiter.api.Test;
import core.NoCard;
import core.Bicycle;
import core.BicycleFactory;
import core.Cards;
import core.ElectricalBicycle;
import core.MechanicalBicycle;
import core.Vlibre;
import core.Vmax;
import core.CardsFactory;
import core.User;

class TestCardsFactory {

	@Test
	void ifCardsRequiredIsVlibreReturnVlibre() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vlibre");
		if (card instanceof Vlibre) {
			assert(true);
		}
		else {
			assert(false);
		}
	}
	
	@Test
	void ifCardsRequiredIsVmaxReturnVmax() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vmax");
		if (card instanceof Vmax) {
			assert(true);
		}
		else {
			assert(false);
		}
	}
	@Test
	void ifCardsRequiredIsNotVmaxNorVlibreReturnNoCard() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("dfdjidfhs");
		if (card instanceof NoCard) {
			assert(true);
		}
		else {
			assert(false);
		}
	}

// the following tests are for users with no cards
	@Test
	public void testPriceNoCardElectricalBicycleFirstHour() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("dfdjidfhs");
	    ElectricalBicycle bike = new ElectricalBicycle();
	    bike.setTime(20);
	    
	    int result = card.visit(bike);
	    
	    // Assert the price charged to the user
	    assertEquals(2, result);
	}
	@Test
	public void testPriceNoCardElectricalBicycleAfterFirstHour() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("dfdjidfhs");
	    ElectricalBicycle bike = new ElectricalBicycle();
	    bike.setTime(140);
	    
	    int result = card.visit(bike);
	    
	    // Assert the price charged to the user
	    assertEquals(6, result);
	}
	@Test
	public void testPriceNoCardMechanicalBicycleFirstHour() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("dfdjidfhs");
	    ElectricalBicycle bike = new ElectricalBicycle();
	    bike.setTime(20);
	    
	    int result = card.visit(bike);
	    
	    // Assert the price charged to the user
	    assertEquals(1, result);
	}
	@Test
	public void testPriceNoCardMechanicalBicycleAfterFirstHour() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("dfdjidfhs");
	    ElectricalBicycle bike = new ElectricalBicycle();
	    bike.setTime(140);
	    
	    int result = card.visit(bike);
	    
	    // Assert the price charged to the user
	    assertEquals(6, result);
	}
//The following set of test is for users with Vlibre
	@Test
	public void testPriceVlibreElectricalBicycleFirstHourNoTimeBalance() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vlibre");
		
	    ElectricalBicycle bike = new ElectricalBicycle();
	    GPS gps = new GPS(48.8566, 2.3522);
        StandardStation station = new StandardStation(5, gps);
	    User Thomas = new User("Thomas", UUID.randomUUID(), card);
	    Thomas.setCurrentBicycle(bike);
	    bike.setUser(Thomas);
		Thomas.setTimecreditBalance(0);
		Thomas.setTotalCharge(1);
		Thomas.returnBike(station,20);
	    
	    //Assert Total Charge correctly changed
	    assertEquals(2, Thomas.gettotalCharge());
	    //Assert correct time balance
	    assertEquals(40, Thomas.getTimecreditBalance());
	}
	
	@Test
	public void testPriceVlibreElectricalBicycleFirstHourUnderTimeBalance() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vlibre");
		GPS gps = new GPS(48.8566, 2.3522);
        StandardStation station = new StandardStation(5, gps);
	    ElectricalBicycle bike = new ElectricalBicycle();
	    User Thomas = new User("Thomas", UUID.randomUUID(), card);
	    Thomas.setCurrentBicycle(bike);
	    bike.setUser(Thomas);
		Thomas.setTimecreditBalance(25);
		Thomas.setTotalCharge(1);
	    Thomas.returnBike(station,20);
	    
	    //Assert Total Charge correctly changed
	    assertEquals(1, Thomas.gettotalCharge());
	    //Assert Total Charge correctly changed
	    assertEquals(5, Thomas.getTimecreditBalance());
	}
	@Test
	public void testPriceVlibreElectricalBicycleFirstHourOverTimeBalance() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vlibre");
		GPS gps = new GPS(48.8566, 2.3522);
        StandardStation station = new StandardStation(5, gps);
	    ElectricalBicycle bike = new ElectricalBicycle();
	    User Thomas = new User("Thomas", UUID.randomUUID(), card);
	    Thomas.setCurrentBicycle(bike);
	    bike.setUser(Thomas);
		Thomas.setTimecreditBalance(10);
		Thomas.setTotalCharge(1);
		Thomas.returnBike(station,20);
	    
	    //Assert Total Charge correctly changed
	    assertEquals(2, Thomas.gettotalCharge());
	    //Assert Total Charge correctly changed
	    assertEquals(50, Thomas.getTimecreditBalance());
	}
	@Test
	public void testPriceVlibreElectricalBicycleAfterHourNoTimeBalance() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vlibre");
		
	    ElectricalBicycle bike = new ElectricalBicycle();
	    GPS gps = new GPS(48.8566, 2.3522);
        StandardStation station = new StandardStation(5, gps);
	    User Thomas = new User("Thomas", UUID.randomUUID(), card);
	    Thomas.setCurrentBicycle(bike);
	    bike.setUser(Thomas);
		Thomas.setTimecreditBalance(0);
		Thomas.setTotalCharge(1);
		Thomas.returnBike(station,140);
	    
	    //Assert Total Charge correctly changed
	    assertEquals(6, Thomas.gettotalCharge());
	    //Assert Total Charge unchanged
	    assertEquals(40, Thomas.getTimecreditBalance());
	}
	
	@Test
	public void testPriceVlibreElectricalBicycleAfterFirstHourUnderTimeBalance() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vlibre");
		
	    ElectricalBicycle bike = new ElectricalBicycle();
	    GPS gps = new GPS(48.8566, 2.3522);
        StandardStation station = new StandardStation(5, gps);
	    User Thomas = new User("Thomas", UUID.randomUUID(), card);
	    Thomas.setCurrentBicycle(bike);
	    bike.setUser(Thomas);
		Thomas.setTimecreditBalance(25);
		Thomas.setTotalCharge(1);
		Thomas.returnBike(station,140);
		
	    //Assert Total Charge correctly changed
	    assertEquals(4, Thomas.gettotalCharge());
	    //Assert Total Charge correctly changed
	    assertEquals(55, Thomas.getTimecreditBalance());
	}
	@Test
	public void testPriceVlibreElectricalBicycleAfterFirstHourOverTimeBalance() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vlibre");
		
	    ElectricalBicycle bike = new ElectricalBicycle();
	    GPS gps = new GPS(48.8566, 2.3522);
        StandardStation station = new StandardStation(5, gps);
	    User Thomas = new User("Thomas", UUID.randomUUID(), card);
	    Thomas.setCurrentBicycle(bike);
	    bike.setUser(Thomas);
		Thomas.setTimecreditBalance(10);
		Thomas.setTotalCharge(1);
		Thomas.returnBike(station,140);
		
	    //Assert Total Charge correctly changed
	    assertEquals(6, Thomas.gettotalCharge());
	    //Assert Total Charge correctly changed
	    assertEquals(50, Thomas.getTimecreditBalance());
	} 
	@Test
	public void testPriceVlibreMechanicalBicycleFirstHourNoTimeBalance() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vlibre");
		MechanicalBicycle bike = new MechanicalBicycle();
		GPS gps = new GPS(48.8566, 2.3522);
        StandardStation station = new StandardStation(5, gps);
	    User Thomas = new User("Thomas", UUID.randomUUID(), card);
	    Thomas.setCurrentBicycle(bike);
	    bike.setUser(Thomas);
		Thomas.setTimecreditBalance(0);
		Thomas.setTotalCharge(1);
	    
		Thomas.returnBike(station,20);
	    //Assert Total Charge correctly changed
	    assertEquals(1, Thomas.gettotalCharge());
	    //Assert Total Charge unchanged
	    assertEquals(0, Thomas.getTimecreditBalance());
	}
	@Test
	public void testPriceVlibreMechanicalBicycleAfterFirstHourNoTimeBalance() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vlibre");
		MechanicalBicycle bike = new MechanicalBicycle();
	    User Thomas = new User("Thomas", UUID.randomUUID(), card);
	    Thomas.setCurrentBicycle(bike);
	    bike.setUser(Thomas);
		Thomas.setTimecreditBalance(0);
		Thomas.setTotalCharge(1);
		GPS gps = new GPS(48.8566, 2.3522);
        StandardStation station = new StandardStation(5, gps);
	    
	    Thomas.returnBike(station,140);
	    
	    
	    //Assert Total Charge correctly changed
	    assertEquals(4, Thomas.gettotalCharge());
	    //Assert Total Charge unchanged
	    assertEquals(40, Thomas.getTimecreditBalance());
	}
	@Test
	public void testPriceVlibreMechanicalBicycleAfterFirstHourUnderTimeBalance() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vlibre");
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
	    assertEquals(2, Thomas.gettotalCharge());
	    //Assert Total Charge unchanged
	    assertEquals(55, Thomas.getTimecreditBalance());
	}
	@Test
	public void testPriceVlibreMechanicalBicycleAfterFirstHourOverTimeBalance() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vlibre");
		MechanicalBicycle bike = new MechanicalBicycle();
		GPS gps = new GPS(48.8566, 2.3522);
        StandardStation station = new StandardStation(5, gps);
	    User Thomas = new User("Thomas", UUID.randomUUID(), card);
	    Thomas.setCurrentBicycle(bike);
	    bike.setUser(Thomas);
		Thomas.setTimecreditBalance(10);
		Thomas.setTotalCharge(1);
	    
	    Thomas.returnBike(station,140);
	    
	    //Assert Total Charge correctly changed
	    assertEquals(3, Thomas.gettotalCharge());
	    //Assert Total Charge unchanged
	    assertEquals(50, Thomas.getTimecreditBalance());
	}

	//The following set of test is for users with Vmax Card
	
	@Test
	public void testPriceVmaxElectricalBicycleFirstHourNoTimeBalance() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vmax");
		
	    ElectricalBicycle bike = new ElectricalBicycle();
	    GPS gps = new GPS(48.8566, 2.3522);
        StandardStation station = new StandardStation(5, gps);
	    User Thomas = new User("Thomas", UUID.randomUUID(), card);
	    Thomas.setCurrentBicycle(bike);
	    bike.setUser(Thomas);
		Thomas.setTimecreditBalance(0);
		Thomas.setTotalCharge(1);
		Thomas.returnBike(station,20);
		
	    //Assert Total Charge correctly changed
	    assertEquals(1, Thomas.gettotalCharge());
	    //Assert Time credit Balance unchanged
	    assertEquals(0, Thomas.getTimecreditBalance());
	}
	@Test
	public void testPriceVmaxElectricalBicycleAfterHourNoTimeBalance() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vmax");
		
	    ElectricalBicycle bike = new ElectricalBicycle();
	    GPS gps = new GPS(48.8566, 2.3522);
        StandardStation station = new StandardStation(5, gps);
	    User Thomas = new User("Thomas", UUID.randomUUID(), card);
	    Thomas.setCurrentBicycle(bike);
	    bike.setUser(Thomas);
		Thomas.setTimecreditBalance(0);
		Thomas.setTotalCharge(1);
		Thomas.returnBike(station,140);
		
	    //Assert Total Charge correctly changed
	    assertEquals(3, Thomas.gettotalCharge());
	    //Assert Total Charge unchanged
	    assertEquals(40, Thomas.getTimecreditBalance());
	}
	
	@Test
	public void testPriceVmaxElectricalBicycleAfterFirstHourUnderTimeBalance() {
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
		
	    //Assert Total Charge correctly changed
	    assertEquals(2, Thomas.gettotalCharge());
	    //Assert Total Charge correctly changed
	    assertEquals(55, Thomas.getTimecreditBalance());
	}
	@Test
	public void testPriceVmaxElectricalBicycleAfterFirstHourOverTimeBalance() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vmax");
		
	    ElectricalBicycle bike = new ElectricalBicycle();
	    GPS gps = new GPS(48.8566, 2.3522);
        StandardStation station = new StandardStation(5, gps);
	    User Thomas = new User("Thomas", UUID.randomUUID(), card);
	    Thomas.setCurrentBicycle(bike);
	    bike.setUser(Thomas);
		Thomas.setTimecreditBalance(10);
		Thomas.setTotalCharge(1);
		Thomas.returnBike(station,140);
		
	    //Assert Total Charge correctly changed
	    assertEquals(3, Thomas.gettotalCharge());
	    //Assert Total Charge correctly changed
	    assertEquals(50, Thomas.getTimecreditBalance());
	} 
	@Test
	public void testPriceVmaxMechanicalBicycleFirstHourNoTimeBalance() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vmax");
		
		MechanicalBicycle bike = new MechanicalBicycle();
		GPS gps = new GPS(48.8566, 2.3522);
        StandardStation station = new StandardStation(5, gps);
	    User Thomas = new User("Thomas", UUID.randomUUID(), card);
	    Thomas.setCurrentBicycle(bike);
	    bike.setUser(Thomas);
		Thomas.setTimecreditBalance(0);
		Thomas.setTotalCharge(1);
		Thomas.returnBike(station,20);
		
	    //Assert Total Charge correctly changed
	    assertEquals(1, Thomas.gettotalCharge());
	    //Assert Time credit Balance unchanged
	    assertEquals(0, Thomas.getTimecreditBalance());
	}
	@Test
	public void testPriceVmaxMechanicalBicycleAfterHourNoTimeBalance() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vmax");
		
		MechanicalBicycle bike = new MechanicalBicycle();
		GPS gps = new GPS(48.8566, 2.3522);
        StandardStation station = new StandardStation(5, gps);
	    User Thomas = new User("Thomas", UUID.randomUUID(), card);
	    Thomas.setCurrentBicycle(bike);
	    bike.setUser(Thomas);
		Thomas.setTimecreditBalance(0);
		Thomas.setTotalCharge(1);
		Thomas.returnBike(station,140);
		
	    //Assert Total Charge correctly changed
	    assertEquals(3, Thomas.gettotalCharge());
	    //Assert Total Charge unchanged
	    assertEquals(40, Thomas.getTimecreditBalance());
	}
	
	@Test
	public void testPriceVmaxMechanicalBicycleAfterFirstHourUnderTimeBalance() {
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
	    assertEquals(2, Thomas.gettotalCharge());
	    //Assert Total Charge correctly changed
	    assertEquals(55, Thomas.getTimecreditBalance());
	}
	@Test
	public void testPriceVmaxMechanicalBicycleAfterFirstHourOverTimeBalance() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vmax");
		
		MechanicalBicycle bike = new MechanicalBicycle();
		GPS gps = new GPS(48.8566, 2.3522);
        StandardStation station = new StandardStation(5, gps);
	    User Thomas = new User("Thomas", UUID.randomUUID(), card);
	    Thomas.setCurrentBicycle(bike);
	    bike.setUser(Thomas);
		Thomas.setTimecreditBalance(10);
		Thomas.setTotalCharge(1);
		Thomas.returnBike(station,140);
		
	    //Assert Total Charge correctly changed
	    assertEquals(3, Thomas.gettotalCharge());
	    //Assert Total Charge correctly changed
	    assertEquals(50, Thomas.getTimecreditBalance());
	} 
	
	@Test
	public void testStationPlusFirstHour() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vmax");
		
		MechanicalBicycle bike = new MechanicalBicycle();
		GPS gps = new GPS(48.8566, 2.3522);
        plusStation station = new plusStation(5, gps);
	    User Thomas = new User("Thomas", UUID.randomUUID(), card);
	    Thomas.setCurrentBicycle(bike);
	    bike.setUser(Thomas);
		Thomas.setTimecreditBalance(10);
		Thomas.returnBike(station,30);
		
	    //Assert Total Charge correctly changed
	    assertEquals(15, Thomas.getTimecreditBalance());
	} 
	
	@Test
	public void testStationPlusAfterFirstHour() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vmax");
		
		MechanicalBicycle bike = new MechanicalBicycle();
		GPS gps = new GPS(48.8566, 2.3522);
        plusStation station = new plusStation(5, gps);
	    User Thomas = new User("Thomas", UUID.randomUUID(), card);
	    Thomas.setCurrentBicycle(bike);
	    bike.setUser(Thomas);
		Thomas.setTimecreditBalance(10);
		Thomas.returnBike(station,140);
		
	    //Assert Total Charge correctly changed
	    assertEquals(55, Thomas.getTimecreditBalance());
	} 
	
}
