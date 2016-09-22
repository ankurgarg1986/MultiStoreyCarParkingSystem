package gojek.parking.Orchestrator;

import static gojek.parking.utils.ResponseMessages.isFree;
import static gojek.parking.utils.ResponseMessages.pakingCreationSuccess;
import static gojek.parking.utils.ResponseMessages.parkingAllocated;
import static gojek.parking.utils.ResponseMessages.parkingCreationFailure;
import static gojek.parking.utils.ResponseMessages.parkingLotFull;
import static gojek.parking.utils.ResponseMessages.parkingNotPresent;
import static gojek.parking.utils.ResponseMessages.slot;
import static gojek.parking.utils.ResponseMessages.slotNumber;
import gojek.entities.Parking;
import gojek.entities.ParkingStatus;
import gojek.entities.Slot;
import gojek.entities.Vehicle;
import gojek.enums.VehicleType;
import gojek.factory.VehicleFactory;
import gojek.parking.builder.ParkingResponseBuilder;
import gojek.parking.builder.ResponseBuilder;
import gojek.parking.contract.GoJekParking;
import gojek.parking.contract.impl.GoJekParkingImpl;
import gojek.parking.exceptions.GoJekException;

import java.util.List;

/**
 * Orchestrator class to convert/call the ParkingManager API's and call the
 * builder to build the final Response It takes the input from command line ,
 * and calls the main contract. If there is any dependency that needs to be
 * created to call the main Library , this layer constructs that . There is no
 * real business logic here.
 * 
 * @author agarg
 *
 */
public class CliOrchestrator implements Orchestrator {

	private GoJekParking gjParking;
	private Parking p;
	private ResponseBuilder rb;

	public CliOrchestrator() {
		this.gjParking = new GoJekParkingImpl();
		rb = new ParkingResponseBuilder();
	}

	@Override
	public String createParkingLot(int n) {
		String resp = null;
		try {
			p = gjParking.createParkingLot(n);
			setParking(p);
			resp = pakingCreationSuccess + String.valueOf(n) + slot;
		} catch (GoJekException e) {
			resp = parkingCreationFailure;
		}
		rb.buildResponse(resp);
		return resp;

	}

	/**
	 * Method to Park the Vehicle
	 * 
	 *
	 */
	@Override
	public String ParkVehicle(String regNumber, String color, VehicleType vType) {
		VehicleFactory vFactory = new VehicleFactory();
		String resp = null;
		try {
			Vehicle v = vFactory.createVehicle(regNumber, color, vType);
			Parking p = getParking();
			Slot slot = gjParking.ParkVehicle(p, v);
			if (slot == null) {
				resp = parkingLotFull;
			} else {
				resp = parkingAllocated + String.valueOf(slot.getId());
			}
			rb.buildResponse(resp);
		} catch (GoJekException ge) {
			// building response for error message . Error response written is
			// defined by me as assignment
			// does not talk about dealing this case.
			resp = ge.getMessage();
			rb.buildResponse(resp);
		}
		return resp;
	}

	/**
	 * Method to free the parking slot
	 */
	@Override
	public String freeSlot(int slotId) {
		Parking p = getParking();
		String resp = null;
		if (p == null) {
			resp = parkingNotPresent;
			rb.buildResponse(resp);
			return resp;
		}
		try {
			gjParking.freeParkingSlot(p, slotId);
			resp = slotNumber + slotId + isFree;
		} catch (GoJekException e) {
			resp = e.getMessage();
		}
		rb.buildResponse(resp);
		return resp;
	}

	@Override
	public void getParkingStatus() {
		p = getParking();
		try {
			List<ParkingStatus> psList = gjParking.getStatusforParking(p);
			rb.buildTabDelimiterResponse(psList);
		} catch (GoJekException e) {
			String resp = e.getMessage();
			rb.buildResponse(resp);
		}
        return;
	}

	/**
	 * getter Method for Parking Object
	 * 
	 * @return
	 */
	public Parking getParking() {
		return p;
	}

	/**
	 * setter Method for Parking Object
	 * 
	 * @return
	 */
	public void setParking(Parking p) {
		this.p = p;
	}

}
