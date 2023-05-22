package core;

public interface Bicycle {

	public int accept(BikeVisitor visitor);
	public int getTime();
}
