package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resource.APIDirectory;
import resource.TestDataBuild;
import resource.Utility;

import static org.junit.Assert.*;

import java.io.IOException;

import static io.restassured.RestAssured.*;


public class AddPlace_Def extends Utility{
	
	
	//global fields
	RequestSpecification request;
	ResponseSpecification common_response;

	Response response_method;
	static Response response;
	
	
	@Given("Adding payload having {string} {string} {string} {string}")
	public void adding_payload_having(String lat, String lng, String name, String address) throws IOException {
	
		double latitude = Double.parseDouble(lat);
		double longitude = Double.parseDouble(lng);
		
		request = given().spec(requestSpecification()).body(TestDataBuild.addPlace(latitude, longitude, name, address));
		
	}

	//for adding place_id

	@Given("Add place Id")
    public void add_place_id() throws Throwable {
		
		String place_id = getPlaceId(response);
		request = given().spec(requestSpecification()).queryParam("place_id" , place_id);
	}
	
	// for delete place
	
	@Given("Adding place Id in payload")
	public void adding_place_Id_in_payload() throws IOException {
		
		
		String place_id = getPlaceId(response);
		request = given().spec(requestSpecification()).body(TestDataBuild.deletePlace(place_id));
	}



	
	
	@When("posting payload in {string} using {string} method")
	public void posting_payload_in_using_method(String resource_api, String http_method) {
	
		//calling api from enum using constructor
		APIDirectory resource = APIDirectory.valueOf(resource_api);
		
		//getting value from variable in enum which is invoked by enum constructor
		//based on http methods
		
		if(http_method.equalsIgnoreCase("post") )
			response_method = request.when().post(resource.getAPIResource());
		
		if(http_method.equalsIgnoreCase("get"))
			response_method = request.when().get(resource.getAPIResource());
		
		if(http_method.equalsIgnoreCase("delete"))
			response_method = request.when().get(resource.getAPIResource());
			
		
	}

	@Then("gives statusCode of {int} on successful execution")
	public void gives_statusCode_of_on_successful_execution(int code) {
	
		// common res is common to all
		common_response = new ResponseSpecBuilder().expectStatusCode(200).build();
	
		//addplace  response is exact response for add place request
		response = response_method.then().spec(common_response).extract().response();

		// checking status code
		assertEquals(response.getStatusCode(), code);

		System.out.println("Status code is "+code);
	}

	@And("{string} equals to {string}")
	public void equals_to(String key, String value) {
	    
		// parsing given key value and checking with expected value
		
		String response_value = response.asString();
		
		JsonPath js = new JsonPath(response_value);
		
		assertEquals(js.getString(key),value);
		System.out.println(key+" is equals to "+value);
		
	}

}
