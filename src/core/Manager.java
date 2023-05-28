package core;

import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class Manager {
	private String name;
	private HashMap<UUID, User> users;
	private HashMap<UUID, DockingStation> stations;
	
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
    	Set<UUID> keySet = stations.keySet();
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
