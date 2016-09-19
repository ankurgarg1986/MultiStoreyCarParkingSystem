package gojek.parking.contract.impl;

import gojek.entities.Parking;
import gojek.entities.ParkingStatus;
import gojek.entities.Slot;
import gojek.entities.Vehicle;
import gojek.parking.builder.ParkingBuilder;
import gojek.parking.contract.ParkingManager;
import gojek.parking.contract.SlotManager;
import gojek.parking.exceptions.GoJekException;
import gojek.parking.utils.ValidateVehicle;

/**
 * Implementation for the  assignment API's
 * @author agarg
 */
public class ParkingManagerImpl implements ParkingManager {

	private SlotManager sm;//dependency on SlotManager
	
	
	@Override
	public Parking createParkingLot(int parkCapacity) throws GoJekException  {
		Parking parking = ParkingBuilder.buildParkingLot(parkCapacity);
		if(parking==null){
			throw new GoJekException("Parking lot could not be created . Please provide slot size greater than 0");
		}
		return parking;
	}

	@Override
	public Slot ParkVehicle(Parking parking, Vehicle vehicle) throws GoJekException {
		if(parking==null){
			throw new GoJekException("Parking lot is not created . Please create parking lot");
		}
		Slot slot = null;
		if(ValidateVehicle.validateVehicle(vehicle)){
			sm = new SlotManagerImpl();
			slot =  sm.findNearestEmptySlot(parking);
			slot = sm.fillSlot(slot,vehicle);		
		}else{
			throw new GoJekException("Invalid Car Object.");
		}
		return slot;
	}

	@Override
	public ParkingStatus getStatusforParking(Parking parking) {
	
		return null;
	}

	@Override
	public void unPark(Parking parking, int parkCapacity) {
		
		
	}
	
	
	
	
	
	
	

}
