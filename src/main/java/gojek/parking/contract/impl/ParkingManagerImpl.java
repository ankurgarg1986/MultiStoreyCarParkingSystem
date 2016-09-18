package gojek.parking.contract.impl;

import gojek.entities.Parking;
import gojek.entities.ParkingStatus;
import gojek.entities.Vehicle;
import gojek.parking.builder.ParkingBuilder;
import gojek.parking.contract.ParkingManager;
import gojek.parking.exceptions.GoJekException;

/**
 * Implementation for the  assignment API's
 * @author agarg
 */
public class ParkingManagerImpl implements ParkingManager {

	@Override
	public Parking createParkingLot(int n) throws GoJekException  {
		Parking p = ParkingBuilder.buildParkingLot(n);
		if(p==null){
			throw new GoJekException("Parking lot could not be created . Please provide slot size greater than 0");
		}
		return p;
	}

	@Override
	public void ParkVehicle(Parking p, Vehicle v) {
		
		
	}

	@Override
	public ParkingStatus getStatusforParking(Parking p) {
	
		return null;
	}

	@Override
	public void unPark(Parking p, int n) {
		
		
	}
	
	
	
	
	
	
	

}
