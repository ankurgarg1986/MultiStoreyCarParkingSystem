package gojek.parking.builder;

import gojek.entities.Parking;
import gojek.entities.MultiStoreyParking;

/**
 * Builder class to build different types of Parking Space.
 * For assignment only MultiStoreyParkingSpace has been created.
 * Can add overloaded or different methods for creating different
 * type of parking space
 * @author agarg
 *
 */
public class ParkingBuilder {
	
	public static Parking buildParkingLot(int n){
		Parking p = null;
		if(n <=0){
			return p;
		}
		p = new MultiStoreyParking(n);
		return p;
	}

}
