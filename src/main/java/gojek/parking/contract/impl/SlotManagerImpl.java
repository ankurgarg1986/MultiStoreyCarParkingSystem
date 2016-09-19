package gojek.parking.contract.impl;

import static gojek.parking.utils.PakingConstants.entryPoint;
import gojek.entities.Parking;
import gojek.entities.SingleDimensionalParkingLot;
import gojek.entities.Slot;
import gojek.entities.Vehicle;
import gojek.enums.ParkingLotType;
import gojek.parking.contract.SlotManager;
import gojek.parking.exceptions.GoJekException;

/**
 * Implementation for Slot Manager . This is the place where actual business
 * logic is written
 * 
 * @author agarg
 *
 */
public class SlotManagerImpl implements SlotManager {

	/**
	 * Method to find Nearest Empty Slot from entry Point
	 * 
	 * @throws GoJekException
	 */
	public Slot findNearestEmptySlot(Parking p) throws GoJekException {
		ParkingLotType pType = p.getpType();
		switch (pType) {
		case SingleFloor:
			SingleDimensionalParkingLot pLot = (SingleDimensionalParkingLot) p;
			return getNearestEmptySlotFromEntryPoint(pLot, entryPoint);
			// can add for other Parking types here
		default:
			throw new GoJekException("Not a valid Parking Lot Type");
		}

	}

	/**
	 * Method returns nearest Slot from entry Point
	 * 
	 * @param pLot
	 * @param point
	 * @return slot. Returns null if parking is full.
	 */
	private Slot getNearestEmptySlotFromEntryPoint(SingleDimensionalParkingLot pLot, int point) {
		Slot[] slots = pLot.getSlots();
		for (int i = point; i < slots.length; i++) {
			if (slots[i].isEmpty()) {
				return slots[i];
			}
		}
		return null;// means parking is full
	}

	
	@Override
	public Slot fillSlot(Slot s , Vehicle v) {
		if(s!=null){
			s.setEmpty(false);
			s.setVehicle(v);
		}
		return s;
	}

}
