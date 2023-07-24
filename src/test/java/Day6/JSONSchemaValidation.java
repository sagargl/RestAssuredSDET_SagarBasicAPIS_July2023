package Day6;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;


import io.restassured.module.jsv.JsonSchemaValidator;

public class JSONSchemaValidation {
	
	@Test
	void jsonschemavalidation()
	{
		given()
		
		.when()
			.get("https://reqres.in/api/users/?page=2")
		
		.then()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("regresJsonSchema.json"));
		
	}

}
