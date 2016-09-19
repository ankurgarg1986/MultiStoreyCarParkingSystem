package gojek.entities;

import gojek.enums.VehicleType;

/**
 * Java POJO to describe a Car .
 * For the problem statement the details mention in the abstract Class Vehicle are enough
 * to describe a Car . But In case any specific detail applicable to Vehicle Type like driverName, chasis Number etc
 * are needed to be added , it can be added here
 * @author agarg
 *
 */
public class Car extends Vehicle {

	
	public Car(String regNumber, String color) {
		super(regNumber, color);
		this.vType = VehicleType.Car;
	}
	
	
	

}
