package core;

import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

	/**
	 * 
	 * 
	 *	<h1> Manager Class </h1>
	 * Sets up a Manager, which allows the supervision of the MyVelib system
	 *The Manager class has access to the system database represented by the HashMap users and stations. 
	 *To set up the data of MyVelib system, one should
	 *	use the addUser fonction with a user object as argument
	 *	use the addStation fonction with a StandardStation object as argument
	 *
	 *The HashMaps permit the use of getUserByID and getStationByID:
	 *The function getUserByID has UUID id as input and returns the associated user object
	 *(à finir)
	 *
	 *Apparte, supervision is an essential step in this kind of project, and I'm currently working with a similar company (Bikeep) and this aspect is their strong point.
	 * */


public class Manager {
	private String name;
	private HashMap<UUID, User> users;
	private HashMap<UUID, DockingStation> stations;
	
	public void setName(String name) {
		this.name=name;
	}
	public Manager(String name) {
        users = new HashMap<>();
        stations = new HashMap<>();
        this.name=name;
        
    }
    public Manager() {
        users = new HashMap<>();
        stations = new HashMap<>();
        
    }
    
    public void sortStation(String sortpolicy) {
    	
    	Collection<DockingStation> values = stations.values();
    	
    	ArrayList<DockingStation> stationList= new ArrayList<DockingStation>(values);
    	
    	if(sortpolicy=="LeastUsed") {
    		LeastUsedStationComparator comparator = new LeastUsedStationComparator();
    		Collections.sort(stationList,comparator);
    	}
    	else if(sortpolicy=="MostUsed") {
    		MostUsedStationComparator comparator = new MostUsedStationComparator();
    		Collections.sort(stationList,comparator);
    	}
    	System.out.println(stationList); // toString for station
    }
    public void addUser(User user) {
        users.put(user.getuserID(), user);
    }
    public void addStation(DockingStation station) {
        stations.put(station.getstationID(), station);
    }

    public User getUserByID(UUID id) {//peut etre que id c'est un string ici parceque uuid?
        return users.get(id);
    }
    public DockingStation getStationByID(UUID id) {
        return stations.get(id);
    }

    public void displayUserReport(UUID id) {
        User user = getUserByID(id);
        if (user != null) {
            System.out.println("User ID: " + user.getuserID());
            System.out.println("Balance: " + user.gettotalCharge());
            System.out.println("Time Credit: " + user.getTimecreditBalance());
            System.out.println("Number of Rides: " + user.getnbRides());
        } else {
            System.out.println("User with ID " + id + " does not exist.");
        }
    }
    public void displayStationReport(UUID id) {
        DockingStation station = getStationByID(id);
        if (station != null) {
            System.out.println("Station ID: " + station.getstationID());
            System.out.println("Is the station working? " + station.isWorking());
            System.out.println("Number of free places: " + station.countFreePlaces());
            System.out.println("Number of occupied places: " + station.countOccupiedPlaces());
            System.out.println("Number of places out of order: " + station.countOutOfOrderPlaces());
        } else {
            System.out.println("Station with ID " + id + " does not exist.");
        }
    }
    public void displayOnlineStations() {
        System.out.println("Online Stations:");
        for (DockingStation station : stations.values()) {
            if (station.isWorking()) {
            System.out.println("Station ID: " + station.getstationID()+ "is working!");
            }
        }
    }

    public void displayOfflineStations() {
        System.out.println("Offline Stations:");
        for (DockingStation station : stations.values()) {
            if (!station.isWorking()) {
            	System.out.println("Station ID: " + station.getstationID());
            }
 
            
        }
    }

    public void displayUsers() {
        System.out.println("Users:");
        for (User user : users.values()) {
            System.out.println("User ID: " + user.getuserID());
        }
    }
    public void displayReportSystem() {
    displayOnlineStations();
    displayOfflineStations();
    displayUsers();
    }
    
}
