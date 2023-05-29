package core;

import java.util.UUID;

/**
 * Vlibre Card implementation : calculate cost and identify a user
 * */
public class Vlibre implements Cards {

	private User user;
	private String cardType;
	
	public Vlibre() {
		
		this.cardType = "Vlibre";
	}
	
	public String getCardType() {
		return cardType;
	}
	


	/**
	 * <H1>Visit an electrical bike to calculate cost</H1>
	 * Return the price that should be charged to the user
	 * Automatically handle setting of time credit balance
	 * 
	 * 
	 * <H3>Beware !</H3> if the time of the bike is not correctly set, the visit won't retunr the good result.
	 * @param bike The bike visited according to visitor pattern
	 * 
	 * Example : 
	 * CardsFactory factory = new CardsFactory();
	 * Cards card = factory.getCards("Vlibre");
	 * User user = new User("Alice",UUID.randomUUID(),card);
	 * ElectricalBicycle bike = new ElectricalBicycle();
	 * bike.setTime(20);
	 * user.getCards().visit(bike);
	 * */
	@Override
	public int visit(ElectricalBicycle bike) {
		int minutes = bike.getTime();
		User user = bike.getUser();
		if (minutes<60) {
			int q = minutes/60;
			int timecredit = user.getTimecreditBalance();
			
			if (timecredit > minutes%60) {
				user.setTimecreditBalance(timecredit - (minutes%60));
				return q;
			}
			else {
				user.setTimecreditBalance(60+timecredit-minutes%60);
				return q+1; // il paye l'heure d'après
			}
		}
		else {
			int q = 2*minutes/60; // 2€ /heure
			int timecredit = user.getTimecreditBalance();
			
			if (timecredit > minutes%60) {
				user.setTimecreditBalance(timecredit - (minutes%60));
				return q;
			}
			else {
				user.setTimecreditBalance(60+timecredit-minutes%60);
				return q+1; // il paye l'heure d'après
			}
			
			
		}
	}

	/**
	 * <H1>Visit a mechanical bike to calculate cost</H1>
	 * Return the price that should be charged to the user
	 * Automatically handle setting of time credit balance
	 * 
	 * 
	 * <H3>Beware !</H3> if the time of the bike is not correctly set, the visit won't retunr the good result.
	 * @param bike The bike visited according to visitor pattern
	 * 
	 * Example : 
	 * 
	 * CardsFactory factory = new CardsFactory();
	 * Cards card = factory.getCards("Vlibre");
	 * User user = new User("Alice",UUID.randomUUID(),card);
	 * MechanicalBicycle bike = new MechanicalBicycle();
	 * bike.setTime(20);
	 * user.getCards().visit(bike);
	 * */
	@Override
	public int visit(MechanicalBicycle bike) {
		int minutes = bike.getTime();
		User user = bike.getUser();
		
		if (minutes<60) {
			return 0;
		}
		else {
			int q = minutes/60;
			int timecredit = user.getTimecreditBalance();
			
			if (timecredit > minutes%60) {
				user.setTimecreditBalance(timecredit - (minutes%60));
				return q;
			}
			else {
				user.setTimecreditBalance(60+timecredit-minutes%60);
				return q+1; // il paye l'heure d'après
			}
			
			
		}
	}

	@Override
	public void setUser(User user) {
		this.user = user;
		
	}
}
