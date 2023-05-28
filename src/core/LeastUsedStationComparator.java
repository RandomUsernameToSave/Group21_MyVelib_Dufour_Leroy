package core;

import java.util.Comparator;

public class LeastUsedStationComparator implements Comparator<DockingStation>{

	@Override
	public int compare(DockingStation o1, DockingStation o2) {
		return Integer.compare(o1.getTotalNumberDropping()-o1.getTotalNumberRenting(),o2.getTotalNumberDropping()- o2.getTotalNumberRenting()); 
	}
}
