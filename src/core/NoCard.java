package core;

public class NoCard implements Cards,BikeVisitor {

	@Override
	public int visit(ElectricalBicycle bike) {
		int minutes = bike.getTime();
		return 2*minutes/60;
		
	}

	@Override
	public int visit(MechanicalBicycle bike) {
		int minutes = bike.getTime();
		return minutes/60;
	}

	@Override
	public int getPrice(int time) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCardType() {
		// TODO Auto-generated method stub
		return null;
	}

}
