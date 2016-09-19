package gojek.test;

import static gojek.test.constants.TestResponseStrings.color;
import static gojek.test.constants.TestResponseStrings.validRegNo;
import gojek.entities.Car;
import gojek.entities.Parking;
import gojek.entities.Vehicle;
import gojek.parking.Orchestrator.CliOrchestrator;
import gojek.parking.Orchestrator.Orchestrator;
import gojek.parking.contract.ParkingManager;
import gojek.parking.contract.impl.ParkingManagerImpl;
import gojek.parking.exceptions.GoJekException;

import org.junit.BeforeClass;

/**
 * 
 * @author agarg
 *
 */
public abstract class AbstractTest {

	protected  static ParkingManager pm;
	protected  static Parking park; // to test apis which expect a parking lot created
	protected  static Orchestrator or;
	protected  static Vehicle validCar;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		 pm = new ParkingManagerImpl();
		 or = new CliOrchestrator();
		 validCar = new Car(validRegNo,color);
		 try {
			park = pm.createParkingLot(10);//to be used by other tests
		} catch (GoJekException e) {
			//doing sysout . In actual better to use logging.
			System.out.println("Parking lot could not be initialized . Please check");
		}
	}

}
