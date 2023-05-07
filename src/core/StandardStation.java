package core;

import java.util.ArrayList;
import java.util.UUID;

public class StandardStation implements DockingStation{
	private UUID stationID;
	private boolean onService;
	private int GPS;
	private ArrayList<Parking> listSlots = new ArrayList<Parking>();
	
	public StandardStation(int nbreParking) {
		this.stationID = java.util.UUID.randomUUID();
		this.onService = true;
		
		for (int i=0; i<nbreParking; ++i){
			this.listSlots.add( new Parking() ); 
		}
		
		
	}
}
