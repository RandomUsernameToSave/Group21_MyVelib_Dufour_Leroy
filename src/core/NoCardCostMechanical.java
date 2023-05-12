package core;

public class NoCardCostMechanical implements costStrategies {

	public int getCost(int minutes,User user) {
		return minutes/60;
	}
}
