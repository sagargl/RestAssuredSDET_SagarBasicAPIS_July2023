package Day4;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class ParsingJSONResponseData {
	
	//Parsing: Traverse through the json response to get the required fields / data from the json response
	//By using JSON Object class. We can6 Parse the JSON Response data
	
	//@Test(priority=1)
	void testJsonResponse()
	{
		//Approach 1:
		/*
		given()
			.contentType("ContentType.JSON")
		
		.when()
			.get("https://reqres.in/api/users/?page=2")
		
		.then()
			.statusCode(200)
			//.header("Content-Type", "text/html; charset=ISO-8859-1")
			.body("data[1].last_name", equalTo("Ferguson"));
		
		*/
		
			/*
			 * Approach 2: 
			 * 1. Here we are storing all the responses in the variable res with the Response Type
			 * 2. res will hold the entire response which will return by the get request
			 * 3. Once we get the response we will do the validation with the TestNG Assertions
			 * 4. Here we no need to use then section., Bcz we are not puting any validation in then section. We are doing in the responnse variable
			 * 
			 */
		Response res = given()
			.contentType("ContentType.JSON")
	
		.when()
			.get("https://reqres.in/api/users/?page=2");
		
		//Validation 1:
			Assert.assertEquals(res.getStatusCode(), 200);
			//Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
			
			//We have to convert the object into string format
			String name=res.jsonPath().get("data[1].last_name").toString();
			Assert.assertEquals(name, "Ferguson");
	
	
	
	}
	
	@Test(priority=2)
	void testJsonResponseBodyData()
	{
		Response res = 
				given()
					.contentType(ContentType.JSON)
		
			.when()
				.get("https://reqres.in/api/users/?page=2");
		
		//From each object i need to capture the lastname. and i need to print all those
		
		/*
		 * JSON Object Class- To traverse or to parse the entire response
		 * Wheneve we got a response and that is into response type, 
		 * If you want to traverse through the JSON response we need to convert into String first and then parse it into JSON Object Class
		 * 
		 */
			//JSON Object class
			//For this we should convert response into String format and then we can parse
			JSONObject jo = new JSONObject(res.asString()); //Converting the response from Response type into to JSON Object type
			
			/*Print all lastnames
			for(int i=0; i<jo.getJSONArray("data").length(); i++)
			{
				String lastname=jo.getJSONArray("data").getJSONObject(i).get("last_name").toString();
				System.out.println("Lastname :" +lastname);
			}
			*/
			
			/* Print specific last name */
			boolean status = false;
			for(int i=0; i<jo.getJSONArray("data").length(); i++)
			{
				String lastname=jo.getJSONArray("data").getJSONObject(i).get("last_name").toString();
				if(lastname.equals("Edwards"))
				{
					status = true;
					break;
				}
			}
				//Last name Not found
				Assert.assertEquals(status, true);
				
				//#Validation 2: Validate id. Note id: is defined it in number format. We need to convert from String to int/double format 
				double totalid = 0;
				
				for(int i=0; i<jo.getJSONArray("data").length(); i++)
				{
					String id=jo.getJSONArray("data").getJSONObject(i).get("id").toString();
					
					totalid = totalid+Double.parseDouble(id); 
				}
				
				System.out.println("Total id of data is :"+totalid);
				Assert.assertEquals(totalid, 57.0);
				
				
			
				
			
	}
	
	

}
