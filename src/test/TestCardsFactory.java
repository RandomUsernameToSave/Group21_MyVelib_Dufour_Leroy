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

class TestCardsFactory {

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
}
