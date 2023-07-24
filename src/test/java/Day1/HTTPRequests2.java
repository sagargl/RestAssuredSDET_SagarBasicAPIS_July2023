package Day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HTTPRequests2 {
	
	int id;
	
	@Test(priority=1)
	void getUsers()
	{
		given()
		.pathParam("mypath", "users") 
		.queryParam("page", 2)
		
		.when()
			//.get("https://reqres.in/api/users?page=2")
			.get("https://reqres.in/api/{mypath}")
			
		
		.then()
		// Validating the status code, 
		//In Response Body validating the page is having an value of 2 or not
		//To display the entire response in the console window
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
		
	}

		
}
