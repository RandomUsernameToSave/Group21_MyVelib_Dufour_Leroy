package core;

import java.util.UUID;

public class CardsFactory {

	public Cards getCards(String cardsType) {
		if (cardsType=="Vlibre") {
			return new Vlibre();
		}
		if (cardsType=="Vmax") {
			return new Vmax();
		}
		return new NoCard();
	}
}
