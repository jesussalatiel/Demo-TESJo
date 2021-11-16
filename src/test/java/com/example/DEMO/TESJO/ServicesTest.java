package com.example.DEMO.TESJO;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class ServicesTest {

	// Document:
	// https://dev.to/leading-edje/testing-your-api-with-java-and-rest-assured-a15
	//DataBase API: https://mixedanalytics.com/blog/list-actually-free-open-no-auth-needed-apis/
	
	@Test
	public void checkEmployeeStatus() {
		// @formatter:off

		  given().
		 
	        when().
	                get("https://www.7timer.info/bin/astro.php").
	        then().
	                assertThat().
	                statusCode(200);
	        // @formatter:on

	}

	@Test
	public void checkInvalidUrlReturns404() throws InterruptedException {
		Thread.sleep(2000);
		// @formatter:off
        given().
        when().
                get("https://www.7timer.info/bin/astro.php2").
        then().
                assertThat().
                statusCode(404);
        // @formatter:on
	}

}
