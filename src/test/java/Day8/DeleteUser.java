package Day8;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {
	@Test
	void test_deleteUser(ITestContext context)
	{
		String bearerToken="911a040ef19a3d9f7854c0d5218c56071e861f3b03c777e43c667f0786be1d1b";
		
		//int id =(Integer) context.getAttribute("user_id");// this should  come from Create User Request. It is access for test level
		int id =(Integer) context.getSuite().getAttribute("user_id"); // this should  come from Create User Request. It is access for suite level
		
		
		given()
			.headers("Authorization","Bearer "+bearerToken)
			.pathParam("id", id)
		.when()
			.delete("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(204)
			.log().all();
		
	}

}
