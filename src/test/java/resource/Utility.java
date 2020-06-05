package resource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utility {

	JsonPath js;
	
	static RequestSpecification common_req;
	
	public   RequestSpecification requestSpecification() throws IOException {
		
		
		if(common_req == null) {
		
			//logging object
		PrintStream stream = new PrintStream(new FileOutputStream("result.txt"));
	
		//filter for logging request and response
		Filter requestFilter = ResponseLoggingFilter.logResponseTo(stream);
		Filter responseFilter = RequestLoggingFilter.logRequestTo(stream);
		
		//common_request specification
		common_req = new RequestSpecBuilder().setBaseUri(globalFile("baseUrl"))
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON)
				.addFilter(requestFilter)
				.addFilter(responseFilter)
				.build();
		
		return common_req;
	}
	
		return common_req;
	}
	
	
	public String globalFile(String key) throws IOException, IOException {
		
		FileInputStream file = new FileInputStream("C:\\Users\\P\\eclipse-workspace\\GoogleMapsAPI\\src\\test\\java\\resource\\globalfile.properties"); 
		Properties property = new Properties();
		property.load(file);
		
		return property.getProperty(key);
	}
	
	
	public String jsonConversion(String response, String key) {
		
		js = new JsonPath(response);
		String value = js.getString(key);
		
		return value;
	}
	
	public String getPlaceId(Response res) {
		
		String jsonresponse = res.asString();
		String place_id = jsonConversion(jsonresponse , "place_id");
		
		return place_id;
	}
	
	
}