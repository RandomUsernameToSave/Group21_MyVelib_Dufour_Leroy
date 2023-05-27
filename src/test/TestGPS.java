package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

import core.GPS;
import org.junit.jupiter.api.Test;

class TestGPS {

	    @Test
	    @DisplayName("Should return zero when both GPS coordinates are the same")
	    void getDistanceWhenCoordinatesAreTheSame() {
	        GPS gps1 = new GPS(40.7128, -74.0060);
	        GPS gps2 = new GPS(40.7128, -74.0060);

	        double distance = gps1.getDistance(gps2);

	        assertEquals(0, distance, 0.001);
	    }

	    @Test
	    @DisplayName("Should return the correct distance between two GPS coordinates")
	    void getDistanceBetweenTwoGPSCoordinates() { // Create two GPS coordinates
	        GPS gps1 = new GPS(40.7128, -74.0060);
	        GPS gps2 = new GPS(37.7749, -122.4194);

	        // Calculate the expected distance between the two GPS coordinates
	        double expectedDistance = 4129086.17;

	        // Call the getDistance method on the first GPS coordinate with the second GPS coordinate as
	        // an argument
	        double actualDistance = gps1.getDistance(gps2);

	        System.out.println(actualDistance);
	        // Assert that the actual distance is equal to the expected distance with a delta of 0.1
	        assertEquals(expectedDistance, actualDistance, 0.1);
	    }
	

}
