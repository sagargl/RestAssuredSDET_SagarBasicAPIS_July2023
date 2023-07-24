package Day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class LoggingDemo {
	
	@Test(priority=1)
	void testLogs()
	{
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			/*
			 * log().all();
			 * It will print entire response in the console window.
			 * Which includes response body, cookies, header info, status code. everything
			 * 
			 * log().body(); --> It will print only body from the response
			 * log().cookies(); --> It will print only cookies from the response
			 * log().headers(); --> It will print only headers info from the response
			 */
			
			.log().body();
	}

}
