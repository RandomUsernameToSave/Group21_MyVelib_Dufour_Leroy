package core;

import java.util.Comparator;

public class MostUsedStationComparator implements Comparator<DockingStation>{

	@Override
	public int compare(DockingStation o1, DockingStation o2) {
		return Integer.compare(o1.getTotalNumberRenting(), o2.getTotalNumberRenting()); 
	}

}
