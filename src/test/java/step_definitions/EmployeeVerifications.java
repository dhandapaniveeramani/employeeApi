package step_definitions;

import org.junit.Assert;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import page_objects.EmployeeValidation;
public class EmployeeVerifications extends EmployeeValidation {
	Response res = null;
	int response_code = 0;
	ResponseBody responseBody;
	StubMapping foo=null;
	WireMockServer wireMockServer;
	
	@Given("^local server is up and running$")
	public void local_server_is_up_and_running() throws Throwable {
		
		
		/*
		 * WireMockServer wireMockServer = new
		 * WireMockServer(wireMockConfig().port(8085)); wireMockServer.start();
		 * WireMock.configureFor("localhost", 8085);
		 */
		EmployeeValidation employeeValidation=new EmployeeValidation();
		employeeValidation.startServer();
		  
		 
	   Response res = RestAssured.given().headers("Content-Type", ContentType.JSON).when().get("http://localhost:8085/employee");
		responseBody = res.getBody(); 
		System.out.println(responseBody.asString());
		  response_code=res.getStatusCode();
		
	}

	@Then("^verify response has successful operation message \"([^\"]*)\"$")
	public void verify_response_has_successful_operation_message(String Description) throws Throwable {
		Assert.assertEquals(200, response_code);
		Assert.assertEquals(Description, responseBody.asString());
	}
		
	@Given("^Navigating to URL \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void navigating_to_URL(String BasicEndPoint, String name, String password) throws Throwable {
		res = RestAssured.given().headers("Content-Type",
				  ContentType.JSON).when() .queryParam("employeename", name)
				  .queryParam("password",password).get(BasicEndPoint);
	}

	@When("^submit method$")
	public void submit_method() throws Throwable {
		ResponseBody responseBody = res.getBody();
		  System.out.println(responseBody.asString());
		  response_code=res.getStatusCode();
	}

	@Then("^verify status code \"([^\"]*)\"$")
	public void verify_status_code(String statusCode) throws Throwable {
		int status_Code=Integer.parseInt(statusCode);
		Assert.assertEquals(status_Code, response_code);
	}

	@Given("^Navigate to url with invalid user$")
	public void navigate_to_url_with_invalid_user() throws Throwable {
	    
		 res = RestAssured.given().headers("Content-Type", ContentType.JSON).when()
				.queryParam("employeename", "dhanda").queryParam("password", "adm")
				.get("http://localhost:8085/employee/login");
		
	}

	@Then("^verify status code is as expected$")
	public void verify_status_code_is_as_expected() throws Throwable {
	   
		responseBody = res.getBody();
		
		int response_code = res.getStatusCode();
		Assert.assertEquals(400, response_code);
	}

	@Given("^Navigate to url to logout \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void navigate_to_url_to_logout(String BasicEndPoint, String name, String password) throws Throwable {
		res = RestAssured.given().headers("Content-Type",
				  ContentType.JSON).when() .queryParam("employeename", name)
				  .queryParam("password",password).get(BasicEndPoint);
	}
}
