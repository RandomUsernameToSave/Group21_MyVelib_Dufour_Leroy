package core;

public class CardsFactory {

	public Cards getCards(String cardsType, int userID) {
		if (cardsType=="Vlibre") {
			return new Vlibre(userID);
		}
		if (cardsType=="Vmax") {
			return new Vmax(userID);
		}
		return null;
	}
}
