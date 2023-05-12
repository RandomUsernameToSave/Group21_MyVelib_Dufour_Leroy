package core;

public class VlibreCostElectrical {
	public int getCost(int minutes,User user) {
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
}
