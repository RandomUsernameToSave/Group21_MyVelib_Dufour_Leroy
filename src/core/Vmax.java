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


	/**
	 * <h1>Visit a mechanical bike to calculate cost</h1>
	 * Return the price that should be charged to the user
	 * Automatically handle setting of time credit balance
	 * 
	 * 
	 * <h3>Beware !</h3> if the time of the bike is not correctly set, the visit won't retunr the good result.
	 * @param bike The bike visited according to visitor pattern
	 * 
	 * Example : 
	 * 
	 * CardsFactory factory = new CardsFactory();
	 * Cards card = factory.getCards("Vmax");
	 * User user = new User("Alice",UUID.randomUUID(),card);
	 * MechanicalBicycle bike = new MechanicalBicycle();
	 * bike.setTime(20);
	 * user.getCards().visit(bike);
	 * */
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

	/**
	 * <h1>Visit an electrical bike to calculate cost</h1>
	 * Return the price that should be charged to the user
	 * Automatically handle setting of time credit balance
	 * 
	 * 
	 * <h3>Beware !</h3> if the time of the bike is not correctly set, the visit won't retunr the good result.
	 * @param bike The bike visited according to visitor pattern
	 * 
	 * Example : 
	 * 
	 * CardsFactory factory = new CardsFactory();
	 * Cards card = factory.getCards("Vmax");
	 * User user = new User("Alice",UUID.randomUUID(),card);
	 * ElectricalBicycle bike = new ElectricalBicycle();
	 * bike.setTime(20);
	 * user.getCards().visit(bike);
	 * */
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
