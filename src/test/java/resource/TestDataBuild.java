package resource;

import java.util.ArrayList;
import java.util.List;

import pojoForMaps.DeletePlace_MainPojo;
import pojoForMaps.Location;
import pojoForMaps.MainPojo;

public class TestDataBuild {
	
	public static MainPojo addPlace(double lat, double lng, String name, String address) {
		
		MainPojo payload = new MainPojo();
		payload.setAccuracy(49.99);
		payload.setAddress(address);
		payload.setLanguage("tamil");
		payload.setName(name);
		payload.setPhone_number("1234567890");
		payload.setWebsite("www.twitter.com");

		Location location = new Location();
		location.setLat(lat);
		location.setLng(lng);
		
		List<String> type = new ArrayList();
		type.add("Enterprise");
		type.add("Bookings");
		
		payload.setLocation(location);
		payload.setTypes(type);

		return payload;
		
	}
	
	public static DeletePlace_MainPojo deletePlace(String place_id) {
		
		DeletePlace_MainPojo payload = new DeletePlace_MainPojo();
		payload.setPlace_id(place_id);
		
		return payload;
	}

}
