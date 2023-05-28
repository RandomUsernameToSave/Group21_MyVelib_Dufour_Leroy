package CLUI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

import core.Bicycle;
import core.BicycleFactory;
import core.Cards;
import core.CardsFactory;
import core.DockingStation;
import core.GPS;
import core.Manager;
import core.OptimalRidePlanning;
import core.Parking;
import core.RidePlanningStrategy;
import core.StandardStation;
import core.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.*;
public class CLIparseCommand {

	private Manager defaultNetwork = new Manager();;
	private ArrayList<Bicycle> bicycleList;
	private HashMap<String, Manager> networks = new HashMap<String, Manager>();
	
	public String displayHelp() {
	String help = "setup <velibnetworkName> \n to create a myVelib network with given name and\r\n"
		+ "consisting of 10 stations each of which has 10 parking slots and such that stations\r\n"
		+ "are arranged on a square grid whose of side 4km and initially populated with a 75%\r\n"
		+ "bikes randomly distributed over the 10 stations\r\n\n"
		
		+ " setup <name> <nstations> <nslots> <s> <nbikes> \n: to create a myVelib net-\r\n"
		+ "work with given name and consisting of nstations stations each of which has nslots\r\n"
		+ "parking slots and such that stations are arranged in as uniform as possible manner\r\n"
		+ "over an area you may assume either being circular of radium s or squared of side s\r\n"
		+ "(please document what kind of area your implementation of this command takes into\r\n"
		+ "account and how stations are distributed over it).Furthermore the network should\r\n"
		+ "be initially populated with a nbikes bikes randomly distributed over the nstations\r\n"
		+ "stations\r\n\n"
		
		+ " addUser <userName,cardType, velibnetworkName> \n : to add a user with name\r\n"
		+ "userName and card cardType (i.e. ``none'' if the user has no card) to a myVelib net-\r\n"
		+ "work velibnetworkName\r\n\n"
		
		+ " offline <velibnetworkName, stationID> \n : to put offline the station stationID\r\n"
		+ "of the myVelib network velibnetworkName\r\n\n"
		
		+ " online <velibnetworkName, stationID> \n : to put online the station stationID of\r\n"
		+ "the myVelib network velibnetworkName\r\n\n"
		
		+ " rentBike <userID, stationID> \n : to let the user userID renting a bike from station\r\n"
		+ "stationID (if no bikes are available should behave accordingly)\r\n\n"
		
		+ " rentBike <userID, GPS_Position> \n : to let the user userID renting a bike parked\r\n"
		+ "at a given GPS_Position\r\n\n"
		
		+ " returnBike <userID, stationID, duration> \n : to let the user userID returning a\r\n"
		+ "bike to station stationID for a given duration (if no parking bay is available should\r\n"
		+ "behave accordingly). This command should display the cost of the rent\r\n\n"
		
		+ " returnBike <userID, GPS_Position, duration> \n : to let the user userID returning\r\n"
		+ "a bike in a given GPS_position for a given duration (if no parking bay is available\r\n"
		+ "should behave accordingly). This command should display the cost of the rent\r\n\n"
		+ 
		" displayStation<velibnetworkName, stationID> \n : to display the statistics (as of\r\n"
		+ "Section 2.4) of station stationID of a myVelib network velibnetwork.\r\n\n"
		
		+ " displayUser<velibnetworkName, userID> \n : to display the statistics (as of Sec-\r\n"
		+ "tion 2.4) of user userID of a myVelib network velibnetwork.\r\n\n"
		
		+ " sortStation<velibnetworkName, sortpolicy> \n : to display the stations in increas-\r\n"
		+ "ing order w.r.t. to the sorting policy (as of Section 2.4) of user sortpolicy.\r\n\n"
		
		+ " display <velibnetworkName> \n : to display the entire status (stations, parking bays,\r\n"
		+ "users) of an a myVelib network velibnetworkName.";
		return help.replace("<", "").replace(">", "").replace(",", " ");
	}
	
	
	
	public void setup(String velibnetworkname) {
		defaultNetwork.setName(velibnetworkname);
		BicycleFactory bicycleFactory = new BicycleFactory();
		ArrayList<DockingStation> listStation = new ArrayList<DockingStation>();
		int N = 10;
		int M = 100;
		
		for (int i=0; i<N;i++) {
			DockingStation temp = new StandardStation(M/N, new GPS(4*Math.random(),4*Math.random()),String.valueOf(i) );
			
			for (Parking park : temp.getListSlots()) {
				if (Math.random()<=0.75) {
					if (Math.random()<=0.3) {
						park.setCurrentBicycle(bicycleFactory.getBicycle("Electrical") );
					}
					else {
						park.setCurrentBicycle(bicycleFactory.getBicycle("Mechanical") );
					}
				}
				
				
			}
			
			listStation.add(temp );
			defaultNetwork.addStation(temp);
			
		}
		networks.put(velibnetworkname,defaultNetwork);
		System.out.println("Setup successful ! ");
	}
	
	public void setup(String velibnetworkname,int nstations,int nslots, double GPSdiameter,int nbikes) {
		Manager manager = new Manager(velibnetworkname);
		BicycleFactory bicycleFactory = new BicycleFactory();
		ArrayList<DockingStation> listStation = new ArrayList<DockingStation>();
		int N = nstations;
		int M = nslots;
		
		for (int i=0; i<N;i++) {
			DockingStation temp = new StandardStation(M/N, new GPS(GPSdiameter*Math.random(),GPSdiameter*Math.random()));
			
			for (Parking park : temp.getListSlots()) {
				if (Math.random()<=nbikes/nslots) {
					if (Math.random()<=0.3) {
						park.setCurrentBicycle(bicycleFactory.getBicycle("Electrical") );
					}
					else {
						park.setCurrentBicycle(bicycleFactory.getBicycle("Mechanical") );
					}
				}
				
				
			}
			
			listStation.add(temp );
			manager.addStation(temp);
			
		}
	}
	
	public void addUser(String userName, String cardType, String velibnetworkName) {
		CardsFactory factory = new CardsFactory();
		Cards card =factory.getCards(cardType);
		
		User user = new User(userName, UUID.randomUUID(), card);
		Manager manager = networks.get(velibnetworkName);
		manager.addUser(user);
		System.out.println("User "+userName+" successfully added to the velib system ! ");
	}

	public void offline(String velibnetworkName, UUID stationID) {
		Manager network = networks.get(velibnetworkName);
		DockingStation station = network.getStationByID(stationID);
		station.setOnService(false);
		System.out.println("Station "+stationID+" set to offline");
	}
	public void online(String velibnetworkName, UUID stationID ) {
		Manager network = networks.get(velibnetworkName);
		DockingStation station = network.getStationByID(stationID);
		station.setOnService(true);
		System.out.println("Station "+stationID+" set to online !");
	}
	public void rentBike(UUID userID,UUID stationID) {
		User user = defaultNetwork.getUserByID(userID);
		DockingStation station = defaultNetwork.getStationByID(stationID);
		if (station.hasBikeType("Mechanical")) {
			user.rentingBike(station, "Mechanical");
			System.out.println("User "+user.getName()+" picked a mechanical bike at "+stationID);
		}
		else if (station.hasBikeType("Electrical")) {
			user.rentingBike(station, "Electrical");
			System.out.println("User "+user.getName()+" picked an electrical bike at "+stationID);
		}
		
		
	}
	
	public void rentBike(UUID userID,GPS GPS_Position) {
		User user = defaultNetwork.getUserByID(userID);
		for (Bicycle bike : bicycleList) {
			if(bike.getGPS()==GPS_Position) {
				user.setCurrentBicycle(bike);
				bike.setUser(user);
			}
		}
	}
	
	public void returnBike(UUID userID,UUID stationId,int duration) {
		User user = defaultNetwork.getUserByID(userID);
		DockingStation station = defaultNetwork.getStationByID(stationId);
		user.returnBike(station, duration);

		System.out.println(user.getName()+" returned his bike at "+stationId);
	}
	public void returnBike(UUID userID,GPS GPSlocation,int duration) {
		User user = defaultNetwork.getUserByID(userID);
		user.returnBike(GPSlocation, duration);
	}
	public void displayStation(String velibnetworkName, UUID stationID) {
		Manager network = networks.get(velibnetworkName);
		network.displayStationReport(stationID);
	}
	public void displayUser(String velibnetworkName, UUID userID) {
		Manager network = networks.get(velibnetworkName);
		network.displayUserReport(userID);
	}
	public void sortStation(String velibnetworkName,String sortpolicy) {
		Manager network = networks.get(velibnetworkName);
		network.sortStation(sortpolicy);
		
	}
	public void display(String velibnetworkName) {
		Manager network = networks.get(velibnetworkName);
		network.displayReportSystem();
	}
	public void parseCommand(String command) {
		String[] parts = command.split(" ");
		if (parts[0].equalsIgnoreCase("setup")) {
			if (parts.length==2) {
				setup(parts[1]);
			}
			else if (parts.length==6) {
				setup(parts[1],Integer.parseInt(parts[2]),Integer.parseInt(parts[3]), Double.parseDouble(parts[4]),Integer.parseInt(parts[5]));
			} else {
	            System.out.println("Invalid command. Please try again.");
	        }
			
        } else if (parts[0].equalsIgnoreCase("addUser") & parts.length==4) {
        	addUser(parts[1],parts[2],parts[3]);
        } else if (parts[0].equalsIgnoreCase("offline") & parts.length==3) {
        	offline(parts[1],UUID.fromString(parts[2]));
        } else if (parts[0].equalsIgnoreCase("online") & parts.length==3) {
        	online(parts[1],UUID.fromString(parts[2]));
        } 
        else if (parts[0].equalsIgnoreCase("rentBike") & parts.length==3) {
        	rentBike(UUID.fromString(parts[1]),UUID.fromString(parts[2]));
        } 
        else if (parts[0].equalsIgnoreCase("rentBike") & parts.length==3) {
        	rentBike(UUID.fromString(parts[1]),GPS.fromString(parts[2]));
        } 
        else if (parts[0].equalsIgnoreCase("returnBike") & parts.length==4) {
        	returnBike(UUID.fromString(parts[1]),UUID.fromString(parts[2]),Integer.parseInt(parts[3]));
        } 
        else if (parts[0].equalsIgnoreCase("returnBike") & parts.length==4) {
        	returnBike(UUID.fromString(parts[1]),GPS.fromString(parts[2]),Integer.parseInt(parts[3]));
        } // check between the 2 return bike
        else if (parts[0].equalsIgnoreCase("returnBike") & parts.length==4) {
        	returnBike(UUID.fromString(parts[1]),UUID.fromString(parts[2]),Integer.parseInt(parts[3]));
        } 
        else if (parts[0].equalsIgnoreCase("displayStation") & parts.length==3) {
        	displayStation(parts[1],UUID.fromString(parts[2]));
        } 
        else if (parts[0].equalsIgnoreCase("displayUser") & parts.length==3) {
        	displayUser(parts[1],UUID.fromString(parts[2]));
        } 
        else if (parts[0].equalsIgnoreCase("sortStation") & parts.length==3) {
        	sortStation(parts[1],parts[2]);
        } 
        else if (parts[0].equalsIgnoreCase("display") & parts.length==2) {
        	display(parts[1]);
        } 
        else if (parts[0].equalsIgnoreCase("help")) {
        	System.out.println(displayHelp());
        }
        else if (parts[0].equalsIgnoreCase("runtest") & parts.length==2) {
        	//initialize
        	runtest(-1);
        	runtest(Integer.parseInt(parts[1]));
        }
		
        else {
            System.out.println("Invalid test command. Please try again.");
        }
	}
	
	public void runtest(int testnumber) {
		String fileName = "./Scenario/testScenario"+testnumber+".txt";
		if (testnumber==-1) {
			fileName = "./Scenario/MyVelib.ini";
		}

		try{
            
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            
            while ((strLine = br.readLine()) != null)   {
              // Print the content on the console
            	String[] parts = strLine.split(" ");
        		if (parts[0].equalsIgnoreCase("setup")) {
        			if (parts.length==2) {
        				setup(parts[1]);
        			}
        			else if (parts.length==6) {
        				setup(parts[1],Integer.parseInt(parts[2]),Integer.parseInt(parts[3]), Double.parseDouble(parts[4]),Integer.parseInt(parts[5]));
        			} else {
        	            System.out.println("Invalid command. Please try again.");
        	        }
        			
                } else if (parts[0].equalsIgnoreCase("addUser") & parts.length==4) {
                	addUser(parts[1],parts[2],parts[3]);
                } else if (parts[0].equalsIgnoreCase("offline") & parts.length==3) {
                	offline(parts[1],UUID.fromString(parts[2]));
                } else if (parts[0].equalsIgnoreCase("online") & parts.length==3) {
                	online(parts[1],UUID.fromString(parts[2]));
                } 
                else if (parts[0].equalsIgnoreCase("rentBike") & parts.length==3) {
                	rentBike(UUID.fromString(parts[1]),UUID.fromString(parts[2]));
                } 
                else if (parts[0].equalsIgnoreCase("rentBike") & parts.length==3) {
                	rentBike(UUID.fromString(parts[1]),GPS.fromString(parts[2]));
                } 
                else if (parts[0].equalsIgnoreCase("returnBike") & parts.length==4) {
                	returnBike(UUID.fromString(parts[1]),UUID.fromString(parts[2]),Integer.parseInt(parts[3]));
                } 
                else if (parts[0].equalsIgnoreCase("returnBike") & parts.length==4) {
                	returnBike(UUID.fromString(parts[1]),GPS.fromString(parts[2]),Integer.parseInt(parts[3]));
                } // check between the 2 return bike
                else if (parts[0].equalsIgnoreCase("returnBike") & parts.length==4) {
                	returnBike(UUID.fromString(parts[1]),UUID.fromString(parts[2]),Integer.parseInt(parts[3]));
                } 
                else if (parts[0].equalsIgnoreCase("displayStation") & parts.length==3) {
                	displayStation(parts[1],UUID.fromString(parts[2]));
                } 
                else if (parts[0].equalsIgnoreCase("displayUser") & parts.length==3) {
                	displayUser(parts[1],UUID.fromString(parts[2]));
                } 
                else if (parts[0].equalsIgnoreCase("sortStation") & parts.length==3) {
                	sortStation(parts[1],parts[2]);
                } 
                else if (parts[0].equalsIgnoreCase("display") & parts.length==2) {
                	display(parts[1]);
                } 
                else if (parts[0].equalsIgnoreCase("help")) {
                	System.out.println(displayHelp());
                }
                else if (parts[0].equalsIgnoreCase("planningRide") & parts.length==4) {
                	planningRide(parts[1],parts[2],parts[3] );
                }
                else {
                    System.out.println("Invalid command. Please try again.");
                }
            	
            	
            	
            	
            }
            //Close the input stream
            fstream.close();
            }catch (Exception e){//Catch exception if any
              System.err.println("Error: " + e.getMessage());
            }
		
	}
	public void planningRide(String GPS1,String GPS2,String bikeType) {
		Collection<DockingStation> values = defaultNetwork.getStations().values();
    	
    	ArrayList<DockingStation> stationList= new ArrayList<DockingStation>(values);

		DockingStation[] plan = (new OptimalRidePlanning()).RidePlanning(GPS.fromString(GPS1),GPS.fromString(GPS2),stationList,bikeType); 

		System.out.println("Here is your starting station GPS: "+ plan[0].getGPS());
		System.out.println("Here is your ending station GPS: "+ plan[1].getGPS());
		
	}
}
