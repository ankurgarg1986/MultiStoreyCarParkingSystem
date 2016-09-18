package gojek.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import gojek.entities.Parking;
import gojek.parking.contract.ParkingManager;
import gojek.parking.contract.impl.ParkingManagerImpl;
import gojek.parking.exceptions.GoJekException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit Tests to test features
 * 
 * @author agarg
 *
 */
public class ParkingTests {

	private static ParkingManager pm;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		 pm = new ParkingManagerImpl();
	}

	@Before
	public void setUp() {

		
	}

	/**
	 * Negative test case to check if parking lot is created of  0 size
	 * @throws GoJekException 
	 * 
	 */
	@Test (expected = GoJekException.class)
	public void testEmptyParkingLotCreation() throws GoJekException {
		Parking p = pm.createParkingLot(0);
		assertNull(p);
	}

	/**
	 * Negative test case to check if parking lot is created of negative size
	 * @throws GoJekException 
	 * 
	 */
	@Test (expected = GoJekException.class)
	public void testNegativeParkingLotCreation() throws GoJekException {
		Parking p = pm.createParkingLot(-5);
		assertNull(p);
	}
	
	/**
	 * Positive test case for checking creation of Parking lot
	 * with non negative and non zero Integer
	 * @throws GoJekException 
	 */
	@Test
	public void testNonEmptyParkingLotCreation() throws GoJekException {
		Parking p = pm.createParkingLot(10);
		assertNotNull(p);
	}

}
