package gojek.parking.contract.impl;

import gojek.entities.Parking;
import gojek.entities.ParkingStatus;
import gojek.entities.Slot;
import gojek.entities.Vehicle;
import gojek.parking.builder.ParkingBuilder;
import gojek.parking.contract.GoJekParking;
import gojek.parking.contract.ParkingManager;
import gojek.parking.exceptions.GoJekException;
import gojek.parking.utils.ParkingUtils;
import gojek.parking.utils.ValidateInput;

import java.util.ArrayList;
import java.util.List;

/**
 * Main Implementation for the assignment API's
 * 
 * @author agarg
 */
public class GoJekParkingImpl implements GoJekParking {

  private ValidateInput vInput;

  public GoJekParkingImpl() {
    this.vInput = new ValidateInput();
  }

  @Override
  public Parking createParkingLot(int parkCapacity) throws GoJekException {
    Parking parking = ParkingBuilder.buildParkingLot(parkCapacity);
    vInput.validateParking(parking);
    return parking;
  }

  /**
   * Thread Safe API to park any vehicle
   */
  @Override
  public Slot ParkVehicle(Parking parking, Vehicle vehicle) throws GoJekException {
    vInput.validateParking(parking);
    Slot slot = null;
    if (vInput.validateVehicle(vehicle)) {
      ParkingManager pm = ParkingUtils.getParkingType(parking);
      // Taking a lock on parking object so that same slot can't be assigned to any other vehicle getting parked
      // Alternatively we can make findNearestEmptySlot and fillParkingSlot thread safe by
      // using synchronized keyword but for that interface definition in ParkingManager needs to be
      // modified which may not be case for all the implementations.
      synchronized (parking) {
        slot = pm.findNearestEmptySlot(parking);
        if (slot != null) {
          pm.fillParkingSlot(slot, vehicle, parking);
          pm.populateRegMap(vehicle, parking);
        }
      }

    } else {
      throw new GoJekException("Invalid Car Object.");
    }
    return slot;
  }

  @Override
  public boolean freeParkingSlot(Parking parking, int slotNumber) throws GoJekException {
    vInput.validateParking(parking);
    vInput.validateSlotNumber(slotNumber);
    boolean result;
    ParkingManager pm = ParkingUtils.getParkingType(parking);

    result = pm.freeParkingSlot(slotNumber, parking);

    if ( !result) {
      throw new GoJekException("Slot Number could not be freed . Slot Number given is invalid.");
    }
    return result;
  }

  @Override
  public List<ParkingStatus> getStatusforParking(Parking parking) throws GoJekException {
    vInput.validateParking(parking);
    ParkingManager pm = ParkingUtils.getParkingType(parking);
    List<ParkingStatus> psList = pm.populateParkingStatus(parking);
    return psList;
  }

  @Override
  public ArrayList<String> getRegistrationNumbers(Parking parking, String color) throws GoJekException {
    vInput.validateParking(parking);
    vInput.validateColorOrRegNumber(color);
    ParkingManager pm = ParkingUtils.getParkingType(parking);
    return pm.getRegistrationNumbersList(parking, color);

  }

  @Override
  public List<Integer> getSlotNumbersForColor(Parking parking, String color) throws GoJekException {
    vInput.validateParking(parking);
    vInput.validateColorOrRegNumber(color);
    ParkingManager pm = ParkingUtils.getParkingType(parking);
    return pm.getSlotNumbersForColor(parking, color);

  }

  @Override
  public Integer getSlotNumberForRegistrationNumber(Parking parking, String regNumber) throws GoJekException {
    vInput.validateParking(parking);
    vInput.validateColorOrRegNumber(regNumber);
    ParkingManager pm = ParkingUtils.getParkingType(parking);
    return pm.getSlotNumbersForRegNumber(parking, regNumber);

  }

}
