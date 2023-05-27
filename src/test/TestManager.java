package test;

import static org.junit.jupiter.api.Assertions.*;
import core.DockingStation;
import core.GPS;
import core.StandardStation;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.io.BufferedReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import junit.framework.Assert;
import core.Manager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

class TestManager {

	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	public void setUp() {
	    System.setOut(new PrintStream(outputStreamCaptor));
	}
	@Test
	void testDisplayStationShouldOutStationID500() {
		DockingStation station = new StandardStation(5, new GPS());
		Manager manager = new Manager();
		manager.addStation(station);
		
		manager.displayStationReport(station.getstationID());
		BufferedReader bufReader = new BufferedReader(new StringReader(outputStreamCaptor.toString().trim()));
		
		String line=null;
		int cpt = 0;
		ArrayList<String> listResults = new ArrayList<String> (Arrays.asList("Station ID: "+station.getstationID(), "Is the station working? true","Number of free places: 5","Number of occupied places: 0","Number of places out of order: 5"));
		try {
			while( (line=bufReader.readLine()) != null )
			{
				
				if (!(listResults.contains(line))) {
				
					assert(false);
				}
				cpt+=1;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		assert(true);
		
	}

	@Test
	void testDisplayOnlineStations() {
		Manager manager = new Manager();
		ArrayList<DockingStation> listStation = new ArrayList<DockingStation>();
		for (int i=0; i<6;i++) {
			listStation.add(new StandardStation(4, new GPS()));
			manager.addStation(listStation.get(i));
		}
		
		
		
		BufferedReader bufReader = new BufferedReader(new StringReader(outputStreamCaptor.toString().trim()));
		
		String line=null;
		int cpt = 0;
		ArrayList<String> listResults = new ArrayList<String>();
		for (int i=0; i<6;i++) {
			listResults.add("Station ID: " + listStation.get(i).getstationID()+ "is working!");
		}
		manager.displayOnlineStations();
		try {
			while( (line=bufReader.readLine()) != null )
			{
				
				if (!(listResults.contains(line))) {
				
					assert(false);
				}
				cpt+=1;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		assert(true);
		
	}
}
