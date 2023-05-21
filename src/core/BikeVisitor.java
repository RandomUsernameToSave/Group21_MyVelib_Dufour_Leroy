package core;

public interface BikeVisitor {

	/**
	 * Bike Visitor Interface
	 * Create the possibility to visit Electrical and Mechanical Bike 
	 * for NoCard, Vlibre, Vmax
	 * */
	public int visit(ElectricalBicycle bike);
	public int visit(MechanicalBicycle bike);
}
