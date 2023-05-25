package core;

public class NoCard implements Cards {

	
	private User user;
	private String cardType;
	
	
	@Override
	public int visit(ElectricalBicycle bike) {
		int minutes = bike.getTime();
		return 2*minutes/60+2;
		
	}

	@Override
	public int visit(MechanicalBicycle bike) {
		int minutes = bike.getTime();
		return minutes/60+1;
	}



	@Override
	public String getCardType() {
		return this.cardType;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
		
	}

}
