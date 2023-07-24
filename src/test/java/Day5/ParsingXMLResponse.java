package Day5;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class ParsingXMLResponse {

	// @Test
	void testXMLResponse() {
		// Approach1:
		given()

				.when().get("http://restapi.adequateshop.com/api/Traveler?page=1")

				.then().statusCode(200).header("Content-Type", "application/xml; charset=utf-8")
				.body("TravelerinformationResponse.page", equalTo("1"))
				.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"));

		// Approach 2:
		Response res = given().contentType("ContentType.JSON")

				.when().get("http://restapi.adequateshop.com/api/Traveler?page=1");

		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");

		String pageNo = res.xmlPath().get("TravelerinformationResponse.page").toString();
		Assert.assertEquals(pageNo, "1");

		String travelerName = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name")
				.toString();
		Assert.assertEquals(travelerName, "Developer");

	}

	@Test
	void testXmlResponseBody() {
		Response res = given()

				.when().get("http://restapi.adequateshop.com/api/Traveler?page=1");

		/*
		 * XML Object Class- To traverse or to parse the entire response Whenever we
		 * got a response and that is into response type, If you want to traverse
		 * through the XML response we need to convert into String first and then parse
		 * it into XML Object Class
		 * 
		 * 
		 */
		// XML Object class
		// For this we should convert response into String format and then we can parse
		XmlPath xmlobj = new XmlPath(res.asString()); // Converting the response from Response type into to JSON Object
														// type

		// Verify total number of travellers

		List<String> travellers = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation");
		Assert.assertEquals(travellers.size(), 10);

		// Verify traveller name is present in response

		List<String> travellers_name = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		boolean status = false;
		for (String travellername : travellers_name) {
			if (travellername.equals("Developer")) {
				status = true;
				break;
			}
		}

		Assert.assertEquals(status, true);

	}

}
