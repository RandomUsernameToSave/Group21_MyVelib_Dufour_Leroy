package core;

public class VmaxCost implements costStrategies {
	
	@Override
	public int getCost(int minutes,User user) {
		if (minutes <60) {
			return 0;
		}
		else {
			return minutes/60;
		}
	}

	

}
