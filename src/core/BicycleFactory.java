package core;

public class BicycleFactory {
	
	public Bicycle getBicycle(String bicycleType) {
		if (bicycleType=="Mechanical") {
			return new MechanicalBicycle();
		}
		if (bicycleType=="Electrical") {
			return new ElectricalBicycle();
		}
		return null;
	}
}
