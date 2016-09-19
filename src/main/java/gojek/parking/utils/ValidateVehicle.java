package gojek.parking.utils;

import gojek.entities.Vehicle;

/**
 * Util class to validate 
 * @author agarg
 *
 */
public class ValidateVehicle {

	public static boolean validateVehicle(Vehicle v){
		if(v.getColor() == null || v.getColor().isEmpty() || v.getRegNumber() == null || v.getRegNumber().isEmpty()){
			return false;
		}
		return true;
		
	}
}
