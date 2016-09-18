package gojek.entities;

/**
 * Class to represent a Parking Ticket.
 * It encapsulates a  slot as Ticket will always be associated with a slot which in turn will have a vehicle 
 * @author agarg
 *
 */
public class Ticket {
	
	
	private Slot slot;
	
	private int ticketNumber;//to maintain serial number of tickets 


	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	public int getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	
	

}
