package core;

public interface BikeVisitor {

	public int visit(ElectricalBicycle bike);
	public int visit(MechanicalBicycle bike);
}
