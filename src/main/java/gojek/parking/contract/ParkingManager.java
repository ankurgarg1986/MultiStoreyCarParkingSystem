package gojek.parking.contract;

import gojek.entities.Parking;
import gojek.entities.ParkingStatus;
import gojek.entities.Vehicle;
import gojek.parking.exceptions.GoJekException;

/**
 * Interface/Contract for exposing the API's which will be used to provide 
 * functionalities the problem expects. This is consumer facing API.
 * Since the current problem expects a command line based input , I have introduced 
 * an additional layer to Orchestrate the command line input and covert it to Parking. This is done to provide
 * loose coupling as tomorrow instead of command line input , one may wish to expose this
 * software as library(jar)  or even a service over web . Current design supports
 * both these or more modifications. Creating a single Contract method means clients do not
 * break if we add more varities of parking in Future . Alternatively, one 
 * can also expose different types of API's to support different types or parking .
 * That decision depends on requirements and product management. I have gone with the below.
 * @author agarg
 *
 */
public interface ParkingManager {
	
	
        public Parking createParkingLot(int n) throws GoJekException ;
        
        public void ParkVehicle(Parking p, Vehicle v);
        
        public ParkingStatus getStatusforParking(Parking p);
        
        public void unPark(Parking p,int n);
	
}
