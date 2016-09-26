package gojek.test.threads;

import gojek.entities.Parking;
import gojek.entities.Slot;
import gojek.entities.Vehicle;
import gojek.parking.contract.GoJekParking;
import gojek.parking.exceptions.GoJekException;

import java.util.concurrent.Callable;

/**
 * Test Class to help unit test the multithreading functionalities.
 * 
 * @author agarg
 *
 */
public class ParkingThreads implements Callable<Slot> {

  private GoJekParking gjParking;
  private Parking park;
  private Vehicle v;

  public ParkingThreads() {

  }

  public ParkingThreads(GoJekParking gjParking, Parking park, Vehicle validCar) {
    this.gjParking = gjParking;
    this.park = park;
    this.v = validCar;
  }

  @Override
  public Slot call() {
    Slot s  = null;
    try {
       s = this.gjParking.ParkVehicle(this.park, this.v);
   } catch (GoJekException e) {
     e.printStackTrace();
   }
    return s;
  }
}
