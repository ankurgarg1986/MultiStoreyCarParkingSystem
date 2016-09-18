package gojek.parking.builder;

import gojek.entities.Car;
import gojek.entities.Vehicle;

/**
 * 
 * Builder class to build different types of Vehicle like car , truck , two wheeler etc.
 * For the current problem , api to build car is defined . But it can be easily
 * extended to build other vehicles . This has been introduced to provide decoupling
 * between Parking and Vehicle Management.
 * @author agarg
 *
 */
public class VehicleBuilder {


	public static Vehicle buildCar(String regNumber, String color) {
		
		Vehicle v = new Car(regNumber,color);
		return v;
	}
}
