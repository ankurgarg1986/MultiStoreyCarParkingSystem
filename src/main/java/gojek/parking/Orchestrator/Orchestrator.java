package gojek.parking.Orchestrator;



import gojek.entities.Parking;
import gojek.enums.VehicleType;

/**
 * Orchestrator class which acts as a Controller to call API's for Parking Lot 
 * Implementation can be provided for both CLI and File
 * @author agarg
 */
public interface Orchestrator {

	public String createParkingLot(int n) ;

	public String ParkVehicle(String regNumber, String color,VehicleType vt);
	
	public String  freeSlot(int slotId);
	
	public Parking getParking();
	
	public void setParking(Parking p);

	public void getParkingStatus();

  public void getRegistrationNumbers(String color);

  public void getSlotNumbersForColor(String color);

  public void getSlotNumbersForRegistrationNumbers(String regNumber);
	

}
