package core;

import java.util.UUID;

/**
 * Vmax Card implementation 
 * */
public class Vmax implements Cards,BikeVisitor {
	private UUID userID;
	private String cardType = "Vmax";
	
	public Vmax(UUID userID) {
		this.userID = userID;
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
}
