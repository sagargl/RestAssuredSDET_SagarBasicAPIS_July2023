package Day3;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class PathAndQueryParameters {
	
	//https://reqres.in/api/users?page=2&id=5
	
	@Test
	void testQueryAndPathParameters()
	{
		given()
			//Path Parameters Key and Value Pairs
			//Key should given any name and the value should be given as Pathname("Users")
			//Same should be apply for Query Param
			.pathParam("mypath", "users") 
			.queryParam("page", 2)
			.queryParam("id", 5)
			
		.when()
			.get("https://reqres.in/api/{mypath}")
			//.get("https://reqres.in/api/users?page=2")
			
		
		.then()
			.statusCode(200)
			.log().all();
		
			
	}

}
