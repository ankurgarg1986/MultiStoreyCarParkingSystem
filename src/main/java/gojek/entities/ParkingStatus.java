package gojek.entities;

/**
 * Class to represent Parking Status
 * which will be send back as response to status Query
 * @author agarg
 *
 */
public class ParkingStatus {
	
	
	private int slotNo;
	
	private String regNumber;
	
	private String color;

	public int getSlotNo() {
		return slotNo;
	}

	public void setSlotNo(int slotNo) {
		this.slotNo = slotNo;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
	

}
