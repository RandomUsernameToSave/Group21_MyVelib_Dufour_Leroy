package CLUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;
/**Main class to run and initialize the CLUI*/
public class Main {

	

	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        CLIparseCommand CLIparse = new CLIparseCommand();
        while (running) {
            System.out.println("Welcome to MyVelib! \nWhat is your request ?");

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
