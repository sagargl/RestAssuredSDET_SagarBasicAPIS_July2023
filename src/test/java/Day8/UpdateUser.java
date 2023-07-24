package Day8;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {
	
	@Test
	void test_UpdateUser(ITestContext context)
	{
		Faker faker =new Faker(); //To generate a random dummy data we user Faker Class
		JSONObject data =new JSONObject();
			data.put("name", faker.name().fullName());
			data.put("gender","Male");
			data.put("email",faker.internet().emailAddress());
			data.put("status", "active");
			
			String bearerToken="911a040ef19a3d9f7854c0d5218c56071e861f3b03c777e43c667f0786be1d1b";
		
			//Response res = given()
		//int id =(Integer) context.getAttribute("user_id"); //this should  come from Create User Request. It is access for test level
		
		int id =(Integer) context.getSuite().getAttribute("user_id"); // this should  come from Create User Request. It is access for suite level
		
		
			given()
				.headers("Authorization","Bearer "+bearerToken)
				.contentType("application/json")
				.pathParam("id", id)
				.body(data.toString())
			
			.when()
				.put("https://gorest.co.in/public/v2/users/{id}")
			.then()
				.statusCode(200)
				.log().all();
				
		
		System.out.println("Generatedid is :"+id);
				
			
				
				
			
	}

}


