package Day8;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class GetUser {
	
	@Test
	void test_getUser(ITestContext context)
	{
	
		//int id =(Integer) context.getAttribute("user_id"); // this should  come from Create User Request. It is access for test level

		int id =(Integer) context.getSuite().getAttribute("user_id"); // this should  come from Create User Request. It is access for suite level
		/*
		 * 1. To refer this variable we have to pass ITestContext variable
		 * 2. To get the value into id we have to use context.getAttribute("user_id")
		 * 3. This method will get the value from user_id which is generated from the previous request. That user_id will be stored in the current id variable
		 */
		
		String bearerToken="911a040ef19a3d9f7854c0d5218c56071e861f3b03c777e43c667f0786be1d1b";
		
		given()
			.headers("Authorization", "Bearer "+bearerToken)
			.pathParam("id", id)
		.when()
			.get("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(200)
			.log().all();
	}
		
	}


