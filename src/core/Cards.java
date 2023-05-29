package core;

/**
 * Cards interface : 3 types : No Card, Vlibre, Vmax*/
public interface Cards {

	public String getCardType();
	public void setUser(User user);
	public int visit(ElectricalBicycle bike);
	public int visit(MechanicalBicycle bike);
}
