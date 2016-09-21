package gojek.parking.contract;

import gojek.entities.Parking;
import gojek.entities.Slot;
import gojek.entities.Vehicle;

/**
 * 
 * @author agarg
 *
 */
public interface ParkingManager {

	public Slot findNearestEmptySlot(Parking parking);

	public void fillParkingSlot(Slot slot, Vehicle vehicle, Parking parking);

	public boolean freeParkingSlot(int slotNumber, Parking parking);

}
