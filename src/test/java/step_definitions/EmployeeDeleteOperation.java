package step_definitions;
import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import page_objects.EmployeeValidation;

public class EmployeeDeleteOperation extends EmployeeValidation {
	
	String password="appleflap4";
	String name="employee4";
	String request="";
	Response response=null;
	int statusCode=0;
	String basicEndPoint="";
	
	@Given("^create request for delete employee operation \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void create_request_for_delete_employee_operation(String basicEndPoint, String name, String password) throws Throwable {
		
		testLoginEmployee();
		RestAssured.baseURI=basicEndPoint;
		
		request = 

						"{ \n" + "  \"name\": \""+name+"\",\n" + "  \"password\": \""+password+"\"\n"
						+ "}";
		
	
	
	}

	@When("^perform delete method$")
	public void perform_delete_method() throws Throwable {
	   
		try
		{
			response=RestAssured.given().contentType(ContentType.JSON).body(request).delete();
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}

	@Then("^verify invalid employee status code as expected$")
	public void verify_invalid_employee_status_code_as_expected() throws Throwable {
		statusCode=response.getStatusCode();
		Assert.assertEquals(400, statusCode);
	}
	
	@Given("^create request for employee not found in delete employee operation$")
	public void create_request_for_employee_not_found_in_delete_employee_operation() throws Throwable {
		testLoginEmployee();
		RestAssured.baseURI="http://localhost:8085/employee";
		
		password="appleflap5";
		name="employee5";
		request = 

						"{ \n" + "  \"name\": \""+name+"\",\n" + "  \"password\": \""+password+"\"\n"
						+ "}";
		
	}

	@Then("^verify employee not found status code$")
	public void verify_employee_not_found_status_code() throws Throwable {
		statusCode=response.getStatusCode();
		Assert.assertEquals(404, statusCode);
	}
	
	@Then("^verify invalid or not found employee status codes \"([^\"]*)\"$")
	public void verify_invalid_or_not_found_employee_status_codes(String statuscode) throws Throwable {
		int status_code=Integer.parseUnsignedInt(statuscode);
		statusCode=response.getStatusCode();
		Assert.assertEquals(status_code, statusCode);
		
	}


}
