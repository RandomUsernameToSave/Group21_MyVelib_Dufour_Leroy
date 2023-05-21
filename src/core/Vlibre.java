package core;

public class Vlibre implements Cards,BikeVisitor {

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
			int q = 2*minutes/60; // 2e /heure
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
}
