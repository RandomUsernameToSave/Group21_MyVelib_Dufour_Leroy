package core;

public class Vmax implements Cards {
	private int userID;
	private String cardType = "Vmax";
	
	public Vmax(int userID) {
		this.userID = userID;
	}
	
	public String getCardType() {
		return cardType;
	}
	public int getPrice(int time) {
		return 0;
	}
}
