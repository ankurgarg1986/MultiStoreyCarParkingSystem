package gojek.entities;

/**
 * Describes a parking Slot
 * This same slot can be extended to support different vehicle types .
 * Today the problem statement only asks to support Cars but later it may be
 * required to support Two Wheelers and Heavy Weight vehicles too. 
 * The design is open for this extension . One way to support such requirement
 * can be to introduce an enum Vehicle Type .Also it can support multidimensional parking
 * as well by introduction of another variable say floor or storey. 
 * @author agarg
 *
 */
public class Slot {
	
	private int id;//for maintaining the Slot Number . 
	private Vehicle vehicle;//details of Vehicle parked in the Slot.
	private boolean isEmpty;//define if a slot is empty or is a vehicle parked
	
	
	/**
	 * constructor to be called when initializing a parking lot
	 * @param isEmpty
	 */
	public Slot(int id){
		this.id = id+1;
		this.isEmpty = true;
	}
	
	/**
	 * Constructor to be called when parking a vehicle in this slot
	 * @param v
	 */
	public Slot(Vehicle v,int id){
		this.vehicle = v;
		this.isEmpty = false;
		this.id = id+1;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}
	
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public boolean isEmpty() {
		return isEmpty;
	}
	
	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	

	
	
	
}
