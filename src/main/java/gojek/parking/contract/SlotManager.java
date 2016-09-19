package gojek.parking.contract;

import gojek.entities.Parking;
import gojek.entities.Slot;
import gojek.entities.Vehicle;
import gojek.parking.exceptions.GoJekException;


/**
 * Contract to manage Slots for a Parking
 * Moving this to a seperate Contract allows Loose Coupling between API Contracts and Business Logic.
 * This is the layer where programming business logic will be written . If needed , one can introduce
 * interaction with any third party service or persistence layer at this point easily without changing
 * the contract exposed to the client (Parking Manager)/ 
 * @author agarg
 *
 */
public interface SlotManager {

	public Slot findNearestEmptySlot(Parking p) throws GoJekException;

	public Slot fillSlot(Slot s, Vehicle v);
	
	
	
	
	
}
