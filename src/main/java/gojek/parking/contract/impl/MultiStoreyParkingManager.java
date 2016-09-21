package gojek.parking.contract.impl;

import static gojek.parking.utils.PakingConstants.entryPoint;
import gojek.entities.MultiStoreyParking;
import gojek.entities.Parking;
import gojek.entities.Slot;
import gojek.entities.Vehicle;
import gojek.parking.contract.ParkingManager;

/**
 * 
 * Implementation for managing MultiStoreyParking Instances
 * @author agarg
 *
 */
public class MultiStoreyParkingManager implements ParkingManager {

	@Override
	public Slot findNearestEmptySlot(Parking parking) {
		MultiStoreyParking mp = (MultiStoreyParking) parking;
		Slot[] slots = mp.getSlots();
		for (int i = entryPoint; i < slots.length; i++) {
			if (slots[i].isEmpty()) {
				return slots[i];
			}
		}
		return null;// means parking is full
	}

	//TODO make thread safe
	@Override
	public void fillParkingSlot(Slot slot, Vehicle vehicle, Parking parking) {
		MultiStoreyParking mp = (MultiStoreyParking) parking;
		int slotNumber = slot.getId();
		if (slot != null) {
			slot.setEmpty(false);
			slot.setVehicle(vehicle);
		}
		Slot[] slots = mp.getSlots();
		slots[slotNumber - 1] = slot;
		mp.setSlots(slots);//not needed but as object reference will be set automatically but added for double check.
		return;

	}

	
	//TODO Make thread safe
	/**
	 * keeping boolean for simplicity . Can be easily modified to send custom Exception or Message 
	 */
	@Override
	public boolean freeParkingSlot(int slotNumber, Parking parking) {
		MultiStoreyParking mp = (MultiStoreyParking) parking;
		int capacity = mp.getCapacity();
		if(slotNumber >= capacity){
			return false;
		}
		Slot[] slots = parking.getSlots();
		Slot slot = slots[slotNumber - 1];
		if (slot.isEmpty()) {
			//slot Number is already free so no need to free it.
			return true;
		}
		slot.setEmpty(true);
		slot.setVehicle(null);
		slots[slotNumber - 1] = slot;
		parking.setSlots(slots);
		return true;

	}

}
