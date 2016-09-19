package gojek.parking.Orchestrator;

import static gojek.parking.utils.ResponseMessages.pakingCreationSuccess;
import static gojek.parking.utils.ResponseMessages.parkingCreationFailure;
import static gojek.parking.utils.ResponseMessages.parkingLotFull;
import static gojek.parking.utils.ResponseMessages.parkingAllocated;
import static gojek.parking.utils.ResponseMessages.slot;
import gojek.entities.Parking;
import gojek.entities.Slot;
import gojek.entities.Vehicle;
import gojek.enums.VehicleType;
import gojek.factory.VehicleFactory;
import gojek.parking.builder.ResponseBuilder;
import gojek.parking.contract.ParkingManager;
import gojek.parking.contract.impl.ParkingManagerImpl;
import gojek.parking.exceptions.GoJekException;

/**
 * Orchestrator class to convert/call the ParkingManager API's and call the
 * builder to build the final Response
 * It takes the input from command line , and calls the main contract. If there is any dependency
 * that needs to be created to call the main Library , this layer constructs that .
 * There is no real business logic here.
 * @author agarg
 *
 */
public class CliOrchestrator implements Orchestrator {

	private ParkingManager pm;
	private Parking p;

	public CliOrchestrator() {
		this.pm = new ParkingManagerImpl();
	}

	@Override
	public String createParkingLot(int n) {
		String resp = null;
		try {
			p = pm.createParkingLot(n);
		    setParking(p);
			resp = pakingCreationSuccess + String.valueOf(n) + slot;
		} catch (GoJekException e) {
			resp = parkingCreationFailure;
		}
		ResponseBuilder.buildResponse(resp);
		return resp;

	}

	/**
	 * Method to Park the Vehicle
	 * 
	 * @throws GoJekException
	 */
	@Override
	public String ParkVehicle(String regNumber, String color,VehicleType vType){
        VehicleFactory vFactory = new VehicleFactory();   
        String resp = null;
		try{
            Vehicle v = vFactory.createVehicle(regNumber, color, vType);
			Parking p = getParking();
            Slot slot = pm.ParkVehicle(p, v);
			if(slot == null){
				resp = parkingLotFull;
			}else{
				resp = parkingAllocated + String.valueOf(slot.getId());
			}
			ResponseBuilder.buildResponse(resp);
		}catch(GoJekException ge){
			//building response for error message . Error response written is defined by me as assignment
			//does not talk about dealing this case.
			resp = ge.getMessage();
			ResponseBuilder.buildResponse(resp);
		}
		return resp;
	}
	
	
	

	/**
	 * getter Method for  Parking Object
	 * @return
	 */
	public Parking getParking() {
		return p;
	}

	/**
	 * setter Method for  Parking Object
	 * @return
	 */
	public void setParking(Parking p) {
		this.p = p;
	}
	
	
	
	
}
