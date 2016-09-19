package gojek.factory;

import gojek.entities.Car;
import gojek.entities.Vehicle;
import gojek.enums.VehicleType;
import gojek.parking.exceptions.GoJekException;

/**
 * 
 * Factory class to build different types of Vehicle like car , truck , two wheeler etc.
 * This has been introduced to provide decoupling
 * between Parking and Vehicle Management.
 * @author agarg
 *
 */
public class VehicleFactory {


	/**
	 * 
	 * @param regNumber
	 * @param color
	 * @return
	 * @throws GoJekException 
	 */
	public Vehicle createVehicle(String regNumber, String color,VehicleType vt) throws GoJekException {
		if(vt.equals(VehicleType.Car)){
			return new Car(regNumber,color);
		}else{
			throw new GoJekException("This vehicle Type  is supported for parking");
		}
	}
}
