package gojek.parking.utils;

import gojek.entities.Parking;
import gojek.entities.Vehicle;
import gojek.parking.exceptions.GoJekException;

/**
 * Validate  class to validate various parameters and throw exceptions accordingly.
 * This will be used throughout the project . It is done to make code more readable 
 * and remove duplicate code.
 * 
 * @author agarg
 *
 */
public class ValidateInput {

	public  boolean isVehicleValid(Vehicle v){
		if(v.getColor() == null || v.getColor().isEmpty() || v.getRegNumber() == null || v.getRegNumber().isEmpty()){
			return false;
		}
		return true;
		
	}

	public  void validateParking(Parking parking) throws GoJekException {
		if(parking==null){
			throw new GoJekException("Parking lot is not created . Please create parking lot");
		}
		
		
	}
	
	public  void validateSlotNumber(int slotNumber) throws GoJekException {
		if(slotNumber <= 0){
			throw new GoJekException(" Invalid input .slotNumber cannot be less than 0");
		}
	}
}
