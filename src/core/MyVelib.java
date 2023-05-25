package core;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class MyVelib {

	public static void main(String[] args) {
		BicycleFactory bicycleFactory = new BicycleFactory();
		CardsFactory cardsFactory = new CardsFactory();
		ArrayList<DockingStation> listStation = new ArrayList<DockingStation>();
		ArrayList<User> listUser = new ArrayList<User>();
		int N = 10;
		int M = 40;
		String[] listName = {"Aaren","Aarika","Abagael","Abagail","Abbe","Abbey","Abbi","Abbie","Abby","Abigael","Abigail","Abigale","Abra"};
		//Create the docking station according to recommendation
		for (int i=0; i<N;i++) {
			DockingStation temp = new StandardStation(M/N, new GPS(Math.random(),Math.random()));
			
			for (Parking park : temp.getListSlots()) {
				if (Math.random()<=0.7) {
					if (Math.random()<=0.3) {
						park.setCurrentBicycle(bicycleFactory.getBicycle("Electrical") );
					}
					else {
						park.setCurrentBicycle(bicycleFactory.getBicycle("Mechanical") );
					}
				}
				
				
			}
			
			listStation.add(temp );
			
		}
		for (int i=0; i<15;i++) {
			int randomNum = ThreadLocalRandom.current().nextInt(0, 12 + 1);
			if (Math.random()>0.4) {
				Cards card = cardsFactory.getCards("");
				listUser.add(new User(listName[randomNum],UUID.randomUUID(), card));
			}
			if (Math.random()>0.4) {
				Cards card = cardsFactory.getCards("Vlibre");
				listUser.add(new User(listName[randomNum],UUID.randomUUID(), card));
			}
			if (Math.random()>0.4) {
				Cards card = cardsFactory.getCards("Vmax");
				listUser.add(new User(listName[randomNum],UUID.randomUUID(), card));
			}
			
			
		}
		/// END OF INITIALISATION ///
		
		
		User Thomas = listUser.get(0);
		// may be able to rent a bike from a given type in the station (without the slot)
		Thomas.rentingBike(listStation.get(0),"Mechanical");
		
		Thomas.returnBike(listStation.get(N-1), 62);
		System.out.println(Thomas.gettotalCharge());
	}
}
