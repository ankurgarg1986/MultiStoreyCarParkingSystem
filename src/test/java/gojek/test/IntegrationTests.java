package gojek.test;

import static gojek.test.constants.TestResponseStrings.createParkingFailure;
import static gojek.test.constants.TestResponseStrings.createParkingSuccess;
import static gojek.test.constants.TestResponseStrings.slotFreeSuccessMessage;
import static gojek.test.constants.TestResponseStrings.slotFreeFailureMessage;
import static gojek.test.constants.TestResponseStrings.lastSlotFreeSuccessMessage;
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
		assertEquals(createParkingSuccess,resp);
	}

	/**
	 * Negative Integration test case for checking failed creation of Parking lot
	 * returns correct message or not
	 * @throws GoJekException 
	 */
	@Test
	public void integrationTestParkingCreationFailedMessage() throws GoJekException {
		String resp = or.createParkingLot(0);
		assertEquals(createParkingFailure,resp);
	}
	
	/**
	 * Positive Integration test case for checking leave command
	 * returns correct message or not
	 * @throws GoJekException 
	 */
	@Test
	public void integrationTestParkingFreeSuccessMessage() throws GoJekException {
		String resp = or.freeSlot(2);
		assertEquals(slotFreeSuccessMessage,resp);
	}
	
	/**
	 * Positve Integration test case for checking leave command
	 * returns correct message or not when slotNumber is equal to capacity
	 * @throws GoJekException 
	 */
	@Test
	public void integrationTestLastSlotParkingFreeSuccessMessage() throws GoJekException {
		String resp = or.freeSlot(5);
		assertEquals(lastSlotFreeSuccessMessage,resp);
	}
	
	/**
	 * Negative Integration test case for checking leave command
	 * returns correct message or not when slotNumber is greater to capacity
	 * @throws GoJekException 
	 */
	@Test
	public void integrationTestParkingFreeFailureMessage() throws GoJekException {
		String resp = or.freeSlot(6);
		assertEquals(slotFreeFailureMessage,resp);
	}



}
