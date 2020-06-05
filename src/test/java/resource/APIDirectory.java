package resource;

public enum APIDirectory {

	addPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");

	private String api_Resource;

	APIDirectory(String api) {
		
		this.api_Resource = api;
	}
	
	
	public String getAPIResource() {
		
		return api_Resource;
	}
}
