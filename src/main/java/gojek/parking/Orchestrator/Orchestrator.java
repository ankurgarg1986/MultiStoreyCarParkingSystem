package gojek.parking.Orchestrator;

import gojek.enums.VehicleType;

/**
 * Orchestrator class which acts as a Controller to call API's for Parking Lot 
 * Implementation can be provided for both CLI and File
 * @author agarg
 */
public interface Orchestrator {

	public String createParkingLot(int n) ;

	public String ParkVehicle(String regNumber, String color,VehicleType vt);

}
