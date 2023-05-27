package core;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

public class Main {
	private Manager defaultNetwork = new Manager();;
	private ArrayList<Bicycle> bicycleList;
	private HashMap<String, Manager> networks;
	
	
	
	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        CLIparseCommand CLIparse = new CLIparseCommand();
        while (running) {
            System.out.println("Welcome to MyVelib!");

            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("quit")) {
                running = false;
            } else {
            	CLIparse.parseCommand(command);
            }
            
            
            
        }

        System.out.println("Thank you for using CLUIApp. Goodbye!");
        scanner.close();
    }

}
