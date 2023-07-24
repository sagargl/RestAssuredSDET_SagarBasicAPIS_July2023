package Day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HTTPRequests {
	
	int id;
	
	@Test(priority=1)
	void getUsers()
	{
		given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
		// Validating the status code, 
		//In Response Body validating the page is having an value of 2 or not
		//To display the entire response in the console window
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
		
	}

	@Test(priority=2)
	void CreateUsers()
	{
		HashMap data =new HashMap();
		data.put("name", "sagar");
		data.put("job", "Software QA");
		
		id=given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		
//		.then()
//			.statusCode(201)
//			.log().all();
//				
	}
	
	@Test(priority=3, dependsOnMethods = {"CreateUsers"})
	void UpdateUser()
	{
		HashMap data =new HashMap();
		data.put("name", "sagargowda");
		data.put("job", "Software Tester");
		
			given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.put("https://reqres.in/api/users/"+id)
			
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority=4)
	void deleteUser()
	{
		given()
		
		.when()
			.delete("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(204)
			.log().all();
		
	}
}
