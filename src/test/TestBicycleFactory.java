package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import core.Bicycle;
import core.BicycleFactory;
import core.Cards;
import core.CardsFactory;
import core.ElectricalBicycle;
import core.MechanicalBicycle;

class TestBicycleFactory {

	@Test
	void ifBicycleRequiredIsElectricalTheReturnedIsElectrical() {
		BicycleFactory Factory = new BicycleFactory();
		Bicycle bike = Factory.getBicycle("Electrical");
		if (bike instanceof ElectricalBicycle) {
			assert(true);
		}
		else {
			assert(false);
		}
	}
	
	@Test
	void ifBicycleRequiredIsMechanicalTheReturnedIsMechanical() {
		BicycleFactory Factory = new BicycleFactory();
		Bicycle bike = Factory.getBicycle("Mechanical");
		if (bike instanceof MechanicalBicycle) {
			assert(true);
		}
		else {
			assert(false);
		}
	}
	@Test
	void ifBicycleRequiredNotMecaOrElectricalReturnNull() {
		BicycleFactory Factory = new BicycleFactory();
		Bicycle bike = Factory.getBicycle("whatever");
		
		if (bike == null) {
			assert(true);
		}
		else {
			assert(false);
		}
	}
	
	@Test
	public void testMechanicalBicycleAcceptVisitor() {
	    MechanicalBicycle bicycle = new MechanicalBicycle();
	    CardsFactory Factory = new CardsFactory();
		Cards visitor = Factory.getCards("Vlibre");
	    int result = bicycle.accept(visitor);
	    
	    // Assert the result returned by the visitor
	    assertEquals(0, result);
	}
	@Test
	public void testElectricalBicycleAcceptVisitor() {
	    ElectricalBicycle bicycle = new ElectricalBicycle();
	    CardsFactory Factory = new CardsFactory();
		Cards visitor = Factory.getCards("Vmax");
	    int result = bicycle.accept(visitor);
	    
	    // Assert the result returned by the visitor
	    assertEquals(0, result);
	}


}
