package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import core.Bicycle;
import core.BicycleFactory;
import core.Cards;
import core.ElectricalBicycle;
import core.MechanicalBicycle;
import core.Vlibre;
import core.Vmax;
import core.CardsFactory;

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
	void ifCardsRequiredIsNotVmaxNorVlibreReturnNull() {
		CardsFactory Factory = new CardsFactory();
		Cards card = Factory.getCards("dfdjidfhs");
		if (card ==null) {
			assert(true);
		}
		else {
			assert(false);
		}
	}

}
