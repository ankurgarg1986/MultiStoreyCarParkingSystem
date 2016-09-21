package gojek.parking.utils;

import gojek.entities.Parking;
import gojek.enums.ParkingLotType;
import gojek.parking.contract.ParkingManager;
import gojek.parking.contract.impl.MultiStoreyParkingManager;
import gojek.parking.exceptions.GoJekException;

/**
 * 
 * @author agarg
 */
public class ParkingUtils {

	public static ParkingManager getParkingType(Parking p) throws GoJekException {
		ParkingLotType pType = p.getpType();
		switch (pType) {
		case SingleFloor:
			return new MultiStoreyParkingManager();
			// can add for other Parking types here
		default:
			throw new GoJekException("Not a valid Parking Lot Type");
		}
		
	}

	
}
