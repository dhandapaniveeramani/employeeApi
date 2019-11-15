package step_definitions;

import org.json.JSONObject;
import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import page_objects.EmployeeValidation;

public class EmployeeGetByName extends EmployeeValidation{
	
	Response res=null;
	int response_code=0;
	int statusCode=0;
	@Given("^create get employee by name request body$")
	public void create_get_employee_by_name_request_body() throws Throwable {
		//Path param is set as a request
		RestAssured.baseURI="http://localhost:8085/employee/employee1";
	
	
	}

	@When("^submit get method$")
	public void submit_get_method() throws Throwable {
          res=RestAssured.given().when().get().then().extract().response();		
        //  response_code=res.getStatusCode();
          statusCode=res.getStatusCode();
		  System.out.println(res.asString());
		
	}
	
	@Then("^verify status code for get employee by name$")
	public void verify_status_code_for_get_employee_by_name() throws Throwable {
		Assert.assertEquals(200, response_code);
	}
	
	@Given("^create get invalid employee by name as request$")
	public void create_get_invalid_employee_by_name_as_request() throws Throwable {
		//Path param is set as a request
		RestAssured.baseURI="http://localhost:8085/employee/nmp";
	}

	@Then("^verify status code for invalid employee$")
	public void verify_status_code_for_invalid_employee() throws Throwable {
		Assert.assertEquals(400, response_code);
	}

	
	@Given("^create request$")
	public void create_request() throws Throwable {
		//Path param is set as a request
				RestAssured.baseURI="http://localhost:8085/employee/employee2";
	}
	
	@When("^perform get method for employee not found$")
	public void perform_get_method_for_employee_not_found() throws Throwable {
		 res=RestAssured.given().when().get().then().extract().response();		
         response_code=res.getStatusCode();
		  System.out.println(res.asString());
	}

	
	@Then("^verify status code for employee not found$")
	public void verify_status_code_for_employee_not_found() throws Throwable {
		Assert.assertEquals(404, response_code);  
	}
	
	@Given("^create get employee by name request body \"([^\"]*)\" \"([^\"]*)\"$")
	public void create_get_employee_by_name_request_body(String BasicEndPoint, String name) throws Throwable {
		RestAssured.baseURI=BasicEndPoint+name;
	}

	@Then("^verify status code for get employee by name \"([^\"]*)\"$")
	public void verify_status_code_for_get_employee_by_name(String StatusCode) throws Throwable {
		int status_code=Integer.parseUnsignedInt(StatusCode);
		statusCode=res.getStatusCode();
		Assert.assertEquals(status_code, statusCode);
	}
}
