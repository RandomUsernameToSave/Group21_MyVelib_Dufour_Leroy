package core;

public class Vlibre implements Cards {

	private int userID;
	private String cardType;
	
	public Vlibre(int userID) {
		this.userID = userID;
		this.cardType = "Vlibre";
	}
	
	public String getCardType() {
		return cardType;
	}
	
	public int getPrice(int time) {
		return 0;
	}
}
