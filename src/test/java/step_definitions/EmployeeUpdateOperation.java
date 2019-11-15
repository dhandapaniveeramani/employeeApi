package step_definitions;
import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import page_objects.EmployeeValidation;
public class EmployeeUpdateOperation extends EmployeeValidation{
	
	String password="appleflap4";
	String name="employee4";
	String request="";
	Response response=null;
	int statusCode=0;
	String basicEndPoint="";
	
	@Given("^create request for update employee operation \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void create_request_for_update_employee_operation(String basicEndPoint, String name, String password) throws Throwable {
	    
		testLoginEmployee();
		RestAssured.baseURI=basicEndPoint;
		
		request = 

						"{ \n" + "  \"name\": \""+name+"\",\n" + "  \"password\": \""+password+"\"\n"
						+ "}";
		
	
	}

	@When("^perform put method$")
	public void perform_put_method() throws Throwable {
	   
		try
		{
			response=RestAssured.given().contentType(ContentType.JSON).body(request).put();
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Then("^verify invalid or not found employee status code \"([^\"]*)\"$")
	public void verify_invalid_or_not_found_employee_status_code(String statuscode) throws Throwable {
		int status_code=Integer.parseUnsignedInt(statuscode);
		statusCode=response.getStatusCode();
		Assert.assertEquals(status_code, statusCode);
		
		stopServer();
	
	}


}
