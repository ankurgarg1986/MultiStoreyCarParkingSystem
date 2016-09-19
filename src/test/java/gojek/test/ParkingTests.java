package gojek.test;

import static gojek.test.constants.TestResponseStrings.color;
import static gojek.test.constants.TestResponseStrings.invalidCarExceptionMessage;
import static gojek.test.constants.TestResponseStrings.validRegNo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import gojek.entities.Car;
import gojek.entities.Parking;
import gojek.entities.Slot;
import gojek.entities.Vehicle;
import gojek.parking.exceptions.GoJekException;

import org.junit.Test;

/**
 * Unit Tests to test various methods
 * 
 * @author agarg
 *
 */
@SuppressWarnings("unused")
public class ParkingTests extends AbstractTest {

	/**
	 * Negative test case to check if parking lot is created of 0 size
	 * 
	 * @throws GoJekException
	 * 
	 */
	@Test(expected = GoJekException.class)
	public void testEmptyParkingLotCreation() throws GoJekException {
		Parking p = pm.createParkingLot(0);
		assertNull(p);
	}

	/**
	 * Negative test case to check if parking lot is created of negative size
	 * 
	 * @throws GoJekException
	 * 
	 */
	@Test(expected = GoJekException.class)
	public void testNegativeParkingLotCreation() throws GoJekException {
		Parking p = pm.createParkingLot(-5);
		assertNull(p);
	}

	/**
	 * Positive test case for checking creation of Parking lot with non negative
	 * and non zero Integer
	 * 
	 * @throws GoJekException
	 */
	@Test
	public void testNonEmptyParkingLotCreation() throws GoJekException {
		Parking p = pm.createParkingLot(10);
		assertNotNull(p);
	}

	/**
	 * Positive test case to test Parking a car with regNo and Color
	 * 
	 * @throws GoJekException
	 */
	@Test
	public void testParkingCar() throws GoJekException {
		Slot s = pm.ParkVehicle(park, validCar);
		assertNotNull(s);

	}

	/**
	 * Negative test case to test Parking a Car with Color Empty Checking if the
	 * exception String is matching here or not
	 */
	@Test
	public void testParkingCarNullColor() {
		Vehicle vehicleEmptyColor = new Car(validRegNo, null);
		String message = null;
		try {
			Slot s = pm.ParkVehicle(park, vehicleEmptyColor);
		} catch (GoJekException e) {
			message = e.getMessage();
		}
		assertNotNull(message);
		//assertEquals(message, invalidCarExceptionMessage);
	}

	/**
	 * Negative test case to test Parking a Car with RegNo Not Passed Checking
	 * if the exception String is matching here or not
	 * 
	 * @throws GoJekException
	 */
	@Test
	public void testParkingCarNullRegNo() {
		Vehicle vehicleEmptyRegNo = new Car(null, color);
		String message = null;
		try {
			Slot s = pm.ParkVehicle(park, vehicleEmptyRegNo);
		} catch (GoJekException e) {
			message = e.getMessage();
		}
		assertNotNull(message);
		//assertEquals(message, invalidCarExceptionMessage);
	}

	/**
	 * Negative test case to test Parking a Car with Color Empty Checking if the
	 * Alternative and better(visually) way to test the last 2 test cases. 
	 * @throws GoJekException
	 */
	@Test(expected = GoJekException.class)
	public void testParkingCarEmptyColor() throws GoJekException {
		Vehicle vehicleEmptyColor = new Car(validRegNo, "");
	    pm.ParkVehicle(park, vehicleEmptyColor);
	}

	/**
	 * Negative test case to test Parking a Car with RegNo Not Passed Checking
	 * 
	 * @throws GoJekException
	 */
	@Test(expected = GoJekException.class)
	public void testParkingCarEmptyRegNo() throws GoJekException {
		Vehicle vehicleEmptyRegNo = new Car("", color);
		pm.ParkVehicle(park, vehicleEmptyRegNo);
	}

	/**
	 * Negative test case to test Parking a Car with Null Parking object
	 * 
	 * @throws GoJekException
	 */
	@Test(expected = GoJekException.class)
	public void testParkingCarNullParking() throws GoJekException {
		pm.ParkVehicle(null, validCar);
	}
	
	/**
	 * Positive test case to test parking for a car with Slot available
	 * @throws GoJekException 
	 */
	@Test
	public void testParkingPositiveSlotAvailable() throws GoJekException {
		Slot s = pm.ParkVehicle(park, validCar);
		assertNotNull(s);
		assertEquals(1, s.getId());
		Vehicle validCar2  = new Car("DL-­12-­AA-­9999","White");
		s = pm.ParkVehicle(park, validCar2);
		assertNotNull(s);
		assertEquals(2,s.getId());
		
	}
	
	/**
	 * Positive test case to test parking for a car with Slot unavailable/Parking Full
	 * @throws GoJekException 
	 */
	@Test
	public void testParkingPositiveParkingFull() throws GoJekException {
		Parking p  =  pm.createParkingLot(1);
		Slot s = pm.ParkVehicle(p, validCar);
		assertNotNull(s);
		assertEquals(1, s.getId());
		Vehicle validCar2  = new Car("DL-­12-­AA-­9999","White");
		s = pm.ParkVehicle(p, validCar2);
		assertNull(s);
		
	}
	
	
	
	
}
