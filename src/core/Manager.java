package core;

import java.util.HashMap;
import java.util.UUID;
//là j'ai importé ça jsp ce que ça fait
public class Manager {
	private HashMap<UUID, User> users;
	private HashMap<UUID, StandardStation> stations;
	

    public Manager() {
        users = new HashMap<>();
        stations = new HashMap<>();
        
    }
    //là on pourrait rajouter des fonctions qui ajoutes des élements si la liste est vide

    public void addUser(User user) {
        users.put(user.getuserID(), user);
    }
    public void addStation(StandardStation station) {
        stations.put(station.getstationID(), station);
    }

    public User getUserByID(int id) {//peut etre que id c'est un string ici parceque uuid?
        return users.get(id);
    }
    public StandardStation getStationByID(int id) {
        return stations.get(id);
    }

    public void displayUserReport(int id) {
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
    public void displayStationReport(int id) {
        StandardStation station = getStationByID(id);
        if (station != null) {
            System.out.println("Station ID: " + station.getstationID());
            System.out.println("Is the station working? " + station.isWorking());
            System.out.println("Number of free places: " + station.countFreePlaces());
            System.out.println("Number of occupied places: " + station.countOccupiedPlaces());
            System.out.println("Number of places out of order: " + station.countOutOfOrderPlaces());
        } else {
            System.out.println("User with ID " + id + " does not exist.");
        }
    }
    public void displayOnlineStations() {
        System.out.println("Online Stations:");
        for (StandardStation station : stations.values()) {
            if (station.isWorking()) {
            System.out.println("Station ID: " + station.getstationID()+ "is working!");
            }
        }
    }

    public void displayOfflineStations() {
        System.out.println("Offline Stations:");
        for (StandardStation station : stations.values()) {
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
