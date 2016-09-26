package gojek.parking.orchestrator;

import static gojek.parking.utils.ResponseMessages.isFree;
import static gojek.parking.utils.ResponseMessages.notFound;
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

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Orchestrator class to convert/call the ParkingManager API's for both command Line and File Based Input
 * Since the implementation is similar for both File based and Command Based Input , so 
 * logic has been moved here. If there is any dependency that needs to be created to
 * call the main Library , this layer constructs that . There is no real business logic here.
 * ThreadSafety - Assuming multiple instances of Parking Lot can be created I am not making it thread safe.
 * Alternatively , one can wrap it inside a singleton if only one instance needs to be created.
 * @author agarg
 *
 */
public class Orchestrator {

  private GoJekParking gjParking;
  private Parking p;
  protected ResponseBuilder rb;
  
  public Orchestrator() {
    this.gjParking = new GoJekParkingImpl();
    this.rb = new ParkingResponseBuilder();
  }
  
  public Orchestrator(Parking p) {
    this.gjParking = new GoJekParkingImpl();
    this.rb = new ParkingResponseBuilder();
    this.p = p;
  }
  
  
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
    }
    return resp;
  }

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

  public void getRegistrationNumbers(String color) {
    p = getParking();
    String resp = null;
    ArrayList<String> regNumbers = null;
    try {
      regNumbers = gjParking.getRegistrationNumbers(p, color);
      rb.buildCommaSeperatedResponse(regNumbers);
    } catch (GoJekException e) {
      resp = e.getMessage();
      rb.buildResponse(resp);
    }
    return;
    
  }

  public void getSlotNumbersForColor(String color) {
    p = getParking();
    String resp = null;
    try {
      List<Integer> slotList = gjParking.getSlotNumbersForColor(p, color);
      if (slotList.size() == 0) {
        rb.buildResponse(notFound);
        return;
      }
      rb.buildCommaSeperatedResponse(slotList);
    } catch (GoJekException e) {
      resp = e.getMessage();
      rb.buildResponse(resp);
    }
    return;
    
  }

  public void getSlotNumbersForRegistrationNumbers(String regNumber) {
    p = getParking();
    try {
      Integer slotNumber = gjParking.getSlotNumberForRegistrationNumber(p, regNumber);
      if (slotNumber  ==  -1) {
        rb.buildResponse(notFound);
        return;
      }
     rb.buildResponse(slotNumber.toString());
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
