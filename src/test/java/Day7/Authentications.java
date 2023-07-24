package Day7;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Authentications {
	//@Test(priority=1)
	void testBasicAuthentication() 
	{
		given()
			.auth().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}

	//@Test(priority=2)
	void testDigestiveAuthentication() 
	{
		given()
			.auth().digest("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	//@Test(priority=3)
	void testPreemtiveAuthentication() 
	{
		given()
			.auth().preemptive().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	//@Test(priority=4)
	void testBearerTokenAuthentication()
	{
		String bearerToken ="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";
		
		given()
			.headers("Authorization","Bearer "+bearerToken)
		
		.when()
			.get("https://api.github.com/user/repos")
			
		
		.then()
			.statusCode(200)
			.log().all();
				
	}
	
	//@Test(priority=5)
	void testOAuth1Authentication()
	{
		given()
			.auth().oauth("consumerkey","consumerSecrat","accessToken","tokenSecrate") //This is for Oath1 Authentication
		
		.when()
			.get("url")
		
		.then()
			.statusCode(200)
			.log().all();
	}	
	
	
	//@Test(priority=6)
	void testOAuth2Authentication()
	{
		given()
			.auth().oauth2("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") //This is for Oath2 Authentication
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.log().all();
	}	
	
	@Test(priority=7)
	void testAPIKeyAuthentication()
	{
		//Method1:
		given()
			.queryParam("appid", "720e146e6072df44710e6ffea42fffac")//appid is API Key
		.when()
			.get("https://openweathermap.org/find?q=bengaluru&appid=720e146e6072df44710e6ffea42fffac")
		.then()
			.statusCode(200)
			.log().all();
		
		//Method2:
		given()
			.queryParam("appid", "720e146e6072df44710e6ffea42fffac")
			.pathParam("mypath", "find")
			.queryParam("q", "bengaluru")
			.queryParam("appid", "720e146e6072df44710e6ffea42fffac")
		
		.when()
			.get("https://openweathermap.org/{mypath}")
			
		.then()
			.statusCode(200)
			.log().all();

		}

}
