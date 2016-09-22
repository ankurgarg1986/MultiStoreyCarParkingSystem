package gojek.parking.contract.impl;

import gojek.entities.Parking;
import gojek.entities.ParkingStatus;
import gojek.entities.Slot;
import gojek.entities.Vehicle;
import gojek.parking.builder.ParkingBuilder;
import gojek.parking.contract.GoJekParking;
import gojek.parking.contract.ParkingManager;
import gojek.parking.exceptions.GoJekException;
import gojek.parking.utils.ParkingUtils;
import gojek.parking.utils.ValidateInput;

import java.util.List;

/**
 * Main Implementation for the  assignment API's
 * @author agarg
 */
public class GoJekParkingImpl implements GoJekParking {

	
	private ValidateInput vInput;
	
	public GoJekParkingImpl(){
		this.vInput = new ValidateInput();
	}
	
	@Override
	public Parking createParkingLot(int parkCapacity) throws GoJekException  {
		Parking parking = ParkingBuilder.buildParkingLot(parkCapacity);
		vInput.validateParking(parking);
		return parking;
	}

	@Override
	public Slot ParkVehicle(Parking parking, Vehicle vehicle) throws GoJekException {
		vInput.validateParking(parking);
		Slot slot = null;
		if(vInput.validateVehicle(vehicle)){
			ParkingManager pm  = ParkingUtils.getParkingType(parking);
		    slot = pm.findNearestEmptySlot(parking);
			if(slot != null) pm.fillParkingSlot(slot,vehicle,parking);
		}else{
			throw new GoJekException("Invalid Car Object.");
		}
		return slot;
	}

	
	@Override
	public boolean freeParkingSlot(Parking parking, int slotNumber) throws GoJekException{
		vInput.validateParking(parking);
		vInput.validateSlotNumber(slotNumber);
		ParkingManager pm  = ParkingUtils.getParkingType(parking);
		boolean result  = pm.freeParkingSlot(slotNumber,parking);
		if(!result){
			throw new GoJekException("Slot Number could not be freed . Slot Number given is invalid.");
		}
		return result;
	}
	
	
	@Override
	public List<ParkingStatus> getStatusforParking(Parking parking) throws GoJekException {
		vInput.validateParking(parking);
		ParkingManager pm  = ParkingUtils.getParkingType(parking);
		List<ParkingStatus> psList = pm.populateParkingStatus(parking);
		return psList;
	}
	
	

}
