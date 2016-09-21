package gojek.test.mocks;

import gojek.entities.Car;
import gojek.entities.MultiStoreyParking;
import gojek.entities.Parking;
import gojek.entities.Slot;
import gojek.entities.Vehicle;

/**
 * Class for Mocking features 
 * which need to be consumed by other tests.
 * In actual projects , some mocking frameworks can be used.
 * Writing my own implementations here for simplicity.
 * @author agarg
 *
 */
public class MockParkingFeatures {
	
	
	public  Parking mockMultiStoreyParkingWithFilledSlots(){
		Vehicle v1 = new Car("KA­-01­-HH­-1234 ","White");
		Vehicle v2 = new Car("KA­-01­-HH­-9999","Black");
		Parking p = new MultiStoreyParking(2);
		Slot[] s = p.getSlots();
		s[0] = new Slot(v1,0);
		s[1] = new Slot(v2,1);
 		return p;
	}
	

}
