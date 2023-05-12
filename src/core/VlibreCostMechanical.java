package core;

public class VlibreCostMechanical implements costStrategies {
	
	public int getCost(int minutes,User user) {
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
