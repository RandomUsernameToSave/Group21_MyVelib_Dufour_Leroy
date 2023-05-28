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
	    assertEquals(3, result);
	}
//The following set of test is for users with Vlibre
@Test
	public void testPriceVlibreElectricalBicycleFirstHour() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vlibre");
	    ElectricalBicycle bike = new ElectricalBicycle();
	    bike.setTime(20);
	    
	    int result = card.visit(bike);
	    
	    // Assert the price charged to the user
	    assertEquals(1, result);
	}
	@Test
	public void testPriceVlibreElectricalBicycleAfterFirstHour() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vlibre");
	    ElectricalBicycle bike = new ElectricalBicycle();
	    bike.setTime(140);
	    
	    int result = card.visit(bike);
	    
	    // Assert the price charged to the user
	    assertEquals(5, result);
	}
	@Test
	public void testPriceVlibreMechanicalBicycleFirstHour() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vlibre");
	    ElectricalBicycle bike = new ElectricalBicycle();
	    bike.setTime(20);
	    
	    int result = card.visit(bike);
	    
	    // Assert the price charged to the user
	    assertEquals(0, result);
	}
	@Test
	public void testPriceVlibreMechanicalBicycleAfterFirstHour() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vlibre");
	    ElectricalBicycle bike = new ElectricalBicycle();
	    bike.setTime(140);
	    
	    int result = card.visit(bike);
	    
	    // Assert the price charged to the user
	    assertEquals(2, result);
	}

//The following set of test is for users with Vmax Card
	@Test
	public void testPriceVmaxElectricalBicycleFirstHour() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vmax");
		User user = new User("Alice",UUID.randomUUID(),card);
	    ElectricalBicycle bike = new ElectricalBicycle();
	    bike.setTime(20);
	    
	    int result = card.visit(bike);
	    
	    // Assert the price charged to the user
	    assertEquals(0, result);
	}
	@Test
	public void testPriceVmaxElectricalBicycleAfterFirstHour() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vmax");
	    ElectricalBicycle bike = new ElectricalBicycle();
	    bike.setTime(140);
	    
	    int result = card.visit(bike);
	    
	    // Assert the price charged to the user
	    assertEquals(2, result);
	}
	@Test
	public void testPriceVmaxMechanicalBicycleFirstHour() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("Vmax");
	    ElectricalBicycle bike = new ElectricalBicycle();
	    bike.setTime(20);
	    
	    int result = card.visit(bike);
	    
	    // Assert the price charged to the user
	    assertEquals(0, result);
	}
	@Test
	public void testPriceVmaxMechanicalBicycleAfterFirstHour() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("max");
	    ElectricalBicycle bike = new ElectricalBicycle();
	    bike.setTime(140);
	    
	    int result = card.visit(bike);
	    
	    // Assert the price charged to the user
	    assertEquals(2, result);
	}
}
