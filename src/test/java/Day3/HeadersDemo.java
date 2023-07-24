package Day3;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class HeadersDemo {

	//@Test(priority=1)
	void testHeaders()
	{
		given()
		
		.when()
			.get("https://www.google.com")
		
		.then()
			/*
			 * 
			 * //For Individual Validation
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.header("Content-Encoding", "gzip")
			.header("Server", "gws");
			*
			*
			*/
			
			
		
		// For multiple Validation
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.and()
			.header("Content-Encoding", "gzip")
			.and()
			.header("Server", "gws")
			.log().headers();
				
		
	}
	
	@Test(priority=2)
	void getHeadersInfo()
	{
		Response res=given()
				
				.when()
					.get("https://www.google.com");
				
			//get Single Header Info. It will return the value of the header
					String headervalue=res.getHeader("Content-Type");
					System.out.println("Value of header is====>"+headervalue);
					
			/*
			 * get All Headers Info. And return type is Headers and we have to import io.restassured.http
			 * Because when we use log().all(); method. It will prints all headers information in console window.
			 * 
			 */
					Headers myheaders=res.getHeaders();
					
					for(Header hd: myheaders)
					{
						System.out.println(hd.getName()+" "+hd.getValue());
					}
					
					
					
				
	}
}
