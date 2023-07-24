package Day8;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
/*
 * 
 * Wherever we have API Chaining we have to execute through xml file**
 */
public class CreateUser {
	
	@Test
	void test_CreateUser(ITestContext context)
	{
		Faker faker =new Faker(); //To generate a random dummy data we user Faker Class
		JSONObject data =new JSONObject();
			data.put("name", faker.name().fullName());
			data.put("gender","Male");
			data.put("email",faker.internet().emailAddress());
			data.put("status", "inactive");
			
			String bearerToken="911a040ef19a3d9f7854c0d5218c56071e861f3b03c777e43c667f0786be1d1b";
		
			//Response res = given()
		int id = given()// If you want to capture id from the response you need to define variable with id type. No need to capture with response variable
				.headers("Authorization","Bearer "+bearerToken)
				.contentType("application/json")
				.body(data.toString())
			
			.when()
				.post("https://gorest.co.in/public/v2/users")
				.jsonPath().getInt("id");
		
		System.out.println("Generatedid is :"+id);
		//context.setAttribute("user_id", id);// This statement will access thru Test level
		context.getSuite().setAttribute("user_id", id); //This statement will access thru suite level
		/*
		 * 
		 * 1. To make the id to other variable we have to pass parameter in test method
		 * 2. Parameter Name will be ITestContext
		 * 3. ITestContext is the interface and with the help of reference variable we will be defining to other class as well
		 * 4. Use this context.setAttribute("user_id", id);-->This statement will make id available to other tests. Just acts like a global variable
		 * 
		 * 
		 */
				
			
				
				
			
	}

}
