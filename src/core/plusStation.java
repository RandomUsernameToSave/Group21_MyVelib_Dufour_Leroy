package core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class plusStation implements DockingStation {
	private UUID stationID;
	private boolean onService;
	private GPS stationGPS;
	private ArrayList<Parking> listSlots = new ArrayList<Parking>();
	
	public plusStation(int nbreParking) {
		this.stationID = java.util.UUID.randomUUID();
		this.onService = true;
		
		for (int i=0; i<nbreParking; ++i){
			this.listSlots.add( new Parking() ); // a voir si on fait pas une classe parking (limite une nested class)
		}
		
		
	}
	
}
