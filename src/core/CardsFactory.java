package core;

import java.util.UUID;

public class CardsFactory {

	public Cards getCards(String cardsType, UUID userID) {
		if (cardsType=="Vlibre") {
			return new Vlibre(userID);
		}
		if (cardsType=="Vmax") {
			return new Vmax(userID);
		}
		return null;
	}
}
