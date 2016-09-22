package gojek.test.mocks;

import gojek.entities.Car;
import gojek.entities.MultiStoreyParking;
import gojek.entities.Parking;
import gojek.entities.ParkingStatus;
import gojek.entities.Slot;
import gojek.entities.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for Mocking features 
 * which need to be consumed by other tests.
 * In actual projects , some mocking frameworks can be used.
 * Writing my own implementations here for simplicity and avoid third party library usage.
 * @author agarg
 *
 */
public class MockParkingFeatures {
	
	private List<ParkingStatus> psList;
	
	
	public  Parking mockMultiStoreyParkingWithFilledSlots(){
		Vehicle v1 = new Car("KA­-01­-HH­-1234 ","White");
		Vehicle v2 = new Car("KA­-01­-HH­-9999","Black");
		Parking p = new MultiStoreyParking(2);
		Slot[] s = p.getSlots();
		s[0] = new Slot(v1,1);
		s[1] = new Slot(v2,2);	
		psList = new ArrayList<ParkingStatus>();
		populateMockParkingStatusList(psList,s);
 		return p;
	}


	private void populateMockParkingStatusList(List<ParkingStatus> psList,Slot[] slots) {
		int len = slots.length;
		for(int i=0;i<len;i++){
			Slot s = slots[i];
			Vehicle v = s.getVehicle();
			ParkingStatus ps = new ParkingStatus(s.getId(),v.getRegNumber(),v.getColor());
			psList.add(ps);
		}
		setPsList(psList);
	}


	public List<ParkingStatus> getPsList() {
		return psList;
	}


	public void setPsList(List<ParkingStatus> psList) {
		this.psList = psList;
	}
	
	
	
	

}
