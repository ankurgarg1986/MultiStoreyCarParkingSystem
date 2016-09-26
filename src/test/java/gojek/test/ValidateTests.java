package gojek.test;

import gojek.parking.exceptions.GoJekException;
import gojek.parking.utils.ValidateInput;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit Tests to test the Util package 
 * @author agarg
 *
 */
public class ValidateTests extends AbstractTest {

  
  private  ValidateInput vInput;
  
  
  @Before
  public void setUp(){
    vInput = new ValidateInput();
  }
  
  @Test
  public void testParkingPositive() throws GoJekException {
    vInput.validateParking(park);
   
  }
  
  @Test(expected=GoJekException.class)
  public void testParkingNegative() throws GoJekException {
    vInput.validateParking(null);
   
  }

  @Test(expected=GoJekException.class)
  public void testSlotNumberNegative() throws GoJekException {
    vInput.validateSlotNumber(-1);
  }
  
  @Test
  public void testSlotNumberPositive() throws GoJekException {
    vInput.validateSlotNumber(2);
  }
  
 
  @Test(expected=GoJekException.class)
  public void testColorOrRegNumberNegative() throws GoJekException {
    vInput.validateColorOrRegNumber("");
  }
  
  @Test
  public void testColorOrRegNumberPositive() throws GoJekException {
    vInput.validateColorOrRegNumber("someString");
  }
  
  

}
