package gojek.entities;

import gojek.entities.Slot;
import gojek.enums.ParkingLotType;

/**
 * 
 * Parking Lot Implementation for managing the parking positions of Vehicles
 * as given in the problem statement .
 * @author agarg
 *
 */
public class SingleDimensionalParkingLot extends Parking {
	
	private Slot[] slots;//describes an array of Parking slots.
	
	/**
	 * constructor to create a Parking slot for the system.
	 * Invoked for call create_parking_lot n
	 * @param n
	 */
	public SingleDimensionalParkingLot(int n){
		super(ParkingLotType.SingleFloor);
		this.slots = new Slot[n];
	}

	public Slot[] getSlots() {
		return slots;
	}

	public void setSlots(Slot[] slots) {
		this.slots = slots;
	}
	
	

}
