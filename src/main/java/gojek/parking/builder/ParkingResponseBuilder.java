package gojek.parking.builder;

import gojek.entities.ParkingStatus;

import java.util.List;
import java.util.ListIterator;

/**
 * 
 * @author agarg
 *
 */
public class ParkingResponseBuilder extends ResponseBuilder {

	@SuppressWarnings("unchecked")
	@Override
	public void buildTabDelimiterResponse(List<? extends Object> obList) {
		System.out.println("SlotNo\tRegistration No\tColor");
		List<ParkingStatus> psList = (List<ParkingStatus>) obList;
		ListIterator<ParkingStatus> it = psList.listIterator();
		while (it.hasNext()) {
			ParkingStatus ps = it.next();
			String color = ps.getColor();
			String regNo = ps.getRegNumber();
			if (color != null && regNo != null && color.length() > 0 && regNo.length() > 0) {
				System.out.println(ps.toStringResponse());
			}
		}
		return;

	}

}
