package core;

import java.util.UUID;

/**
 * Vmax Card implementation 
 * */
public class Vmax implements Cards {
	private User user;
	private String cardType = "Vmax";
	
	public Vmax() {

	}
	
	public String getCardType() {
		return cardType;
	}
	public int getPrice(int time) {
		return 0;
	}

	@Override
	public int visit(ElectricalBicycle bike) {
		int minutes = bike.getTime();
		if (minutes <60) {
			return 0;
		}
		else {
			return minutes/60;
		}
	}

	@Override
	public int visit(MechanicalBicycle bike) {
		int minutes = bike.getTime();
		if (minutes <60) {
			return 0;
		}
		else {
			return minutes/60;
		}
	}
	public void setUser(User user) {
		this.user = user;
		
	}
}
