package gojek.test;

import static gojek.test.constants.TestResponseStrings.createParkingFailure;
import static gojek.test.constants.TestResponseStrings.createParkingSuccess;
import static org.junit.Assert.assertEquals;
import gojek.parking.exceptions.GoJekException;

import org.junit.Test;

/**
 * Integration tests to test features end to end
 * @author agarg
 *
 */
public class IntegrationTests  extends AbstractTest{
	
	
	/**
	 * Positive Integration test case for checking creation of Parking lot
	 * returns correct message or not
	 * @throws GoJekException 
	 */
	@Test
	public void integrationTestParkingCreationSuccessMessage() throws GoJekException {
		String resp = or.createParkingLot(6);
		assertEquals(resp,createParkingSuccess);
	}

	/**
	 * Negative Integration test case for checking failed creation of Parking lot
	 * returns correct message or not
	 * @throws GoJekException 
	 */
	@Test
	public void integrationTestParkingCreationFailedMessage() throws GoJekException {
		String resp = or.createParkingLot(0);
		assertEquals(resp,createParkingFailure);
	}

}
