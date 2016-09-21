package gojek.test;

import static gojek.test.constants.TestResponseStrings.color;
import static gojek.test.constants.TestResponseStrings.validRegNo;
import gojek.entities.Car;
import gojek.entities.Parking;
import gojek.entities.Vehicle;
import gojek.parking.Orchestrator.CliOrchestrator;
import gojek.parking.Orchestrator.Orchestrator;
import gojek.parking.contract.GoJekParking;
import gojek.parking.contract.impl.GoJekParkingImpl;
import gojek.parking.exceptions.GoJekException;
import gojek.test.mocks.MockParkingFeatures;

import org.junit.BeforeClass;

/**
 * 
 * @author agarg
 *
 */
public abstract class AbstractTest {

	protected static GoJekParking gjParking;
	protected static Parking park; // to test apis which expect a parking lot created						
	protected static Orchestrator or;
	protected static Vehicle validCar;
	protected static MockParkingFeatures mp;

	@BeforeClass
	public static void setUpBeforeClass() {
		gjParking = new GoJekParkingImpl();
		or = new CliOrchestrator();
		mp = new MockParkingFeatures();
		validCar = new Car(validRegNo, color);
		try {
			park = gjParking.createParkingLot(5);// to be used by other tests
		} catch (GoJekException e) {
			// doing sysout . In actual better to use logging.
			System.out
					.println("Parking lot could not be initialized . Please check");
		}
		or.setParking(park);
	}

}
