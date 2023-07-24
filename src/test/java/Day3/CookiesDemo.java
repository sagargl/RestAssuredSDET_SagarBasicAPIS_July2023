package Day3;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesDemo {

	//@Test(priority=1)
	void testCookies()
	{
		given()
		
		.when()
			.get("https://www.google.com")
		
		.then()
			.cookie("AEC","AUEFqZfHfIKO3DceygY8VU7Jt4uEOW2_DkRrnYnMOQtgmV2fh9JOcisaOJw")
			.log().all();
	}
	
	@Test(priority=2)
	void getCookiesInfo()
	{
		Response res=given()
		
		.when()
			.get("https://www.google.com");
		
		//get Single Cookie Info. It will return the value of the Cookie
		String COOKIE_VALUE=res.getCookie("AEC");
		System.out.println("Value of Cookie is====>"+COOKIE_VALUE);
		
		//get All Cookies Info
		Map<String,String> cookies_values=res.getCookies();
		

		// Will return Specific Cookie Key Information. If we want to extract all the keys we should return KeySet Method
		System.out.println(cookies_values.keySet());
		
		//This will Print all the Cookie Name With value info
		for(String key : cookies_values.keySet())
		{
			String cookie_value=res.getCookie(key);
			System.out.println(key+ "   "+cookie_value);
		}
		
		
		 
		
		
		
	}
}
