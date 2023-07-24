package Day2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;


public class DifferentWaysToCreatePostRequestBody {
	
	//1. Post request body using HashMap
	
	//@Test
	void testPostUsingHashMap()
	{
		HashMap data=new HashMap();
		data.put("name", "Scott");
		data.put("job", "SDET");
		
		//String coursesArr[]= {"C","C++"};
		//data.put("courses", coursesArr);
		
		given()
			.contentType("application/json")
			.body(data)
			
			
		.when()
			.post("44")
		
		.then()
			.statusCode(201)
			.body("name", equalTo("Scott"))
			//.body("courses[0]",equalTo("C"))
			//.body("courses[0]",equalTo("C++"))
			.header("Content-Type","application/json; charset=utf-8")
			.log().all();
		}
	
	//2. Post request body using Org.json library
	//@Test
	void testPostUsingJsonLibrary()
	{
		JSONObject data1 = new JSONObject();
		data1.put("name", "Tiger");
		data1.put("job", "Dev");
		
		given()
			.contentType("application/json")
			.body(data1.toString())
			/*When we use HashMap we can pass data directly to body. 
			 * But in org.json we cannot pass directly into the body.
			 * For that we need to convert into String format - .body(data.toString())
			 *  
			 */
			
		.when()
			.post("https://reqres.in/api/users")
	
		.then()
			.statusCode(201)
			.body("name", equalTo("Tiger"))
			//.body("courses[0]",equalTo("C"))
			//.body("courses[0]",equalTo("C++"))
			.header("Content-Type","application/json; charset=utf-8")
			.log().all();
	
		
	}
	
	//3. Post Request Using POJO(Plain Old Java Object) Class
	
	//@Test(priority=3)
	void testPostUsingPOJO()
	{
	
	POJO_PostRequest data2 = new POJO_PostRequest();
	data2.setName("Mark");
	data2.setJob("DBA");
	
	String coursesArr[]= {"Java","Python"};
	data2.setCourses(coursesArr);
	
	given()
		.contentType("application/json")
		.body(data2)
		
	.when()
		.post("https://reqres.in/api/users")

	.then()
		.statusCode(201)
		.body("name", equalTo("Mark"))
		//.body("courses[0]",equalTo("C"))
		//.body("courses[0]",equalTo("C++"))
		.header("Content-Type","application/json; charset=utf-8")
		.log().all();


	
	}
	
	//4. Post Request Using External JSON File
	@Test(priority=4)
	void testPostUsingExternalJsonFile() throws FileNotFoundException
	{
	
	File file = new File(".\\body.json");
	FileReader fr =new FileReader(file);
	JSONTokener jt =new JSONTokener(fr);
	JSONObject data4 =new JSONObject(jt);
	
	given()
	.contentType("application/json")
	.body(data4.toString())
	
.when()
	.post("https://reqres.in/api/users")

.then()
	.statusCode(201)
	.body("name", equalTo("John"))
	//.body("courses[0]",equalTo("C"))
	//.body("courses[0]",equalTo("C++"))
	.header("Content-Type","application/json; charset=utf-8")
	.log().all();

}
}