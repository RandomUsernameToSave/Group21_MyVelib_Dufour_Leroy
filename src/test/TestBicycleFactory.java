package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import core.Bicycle;
import core.BicycleFactory;
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

}
