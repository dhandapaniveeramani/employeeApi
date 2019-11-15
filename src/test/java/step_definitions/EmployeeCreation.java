package step_definitions;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import page_objects.EmployeeValidation;

public class EmployeeCreation extends EmployeeValidation{
	
	String password="appleflap1";
	String name="employee1";
	String body;
	 Response res;
	 ResponseBody responseBody;
	 String password1="appleflap1";
	  String employeename1="employee1";
	  String password2="appleflap2";
	  String employeename2="employee2";
	  String bodyWithArrayInput;
	  int id1=100;
	  String name1="employee1";
	  int id2=200;
	  String name2="employee2";
	  String bodyWithListInput;
	
	@Given("^create request body$")
	public void create_request_body() throws Throwable {
	   
		testLoginEmployee();
		 
		
		  body =
		  
		  "{ \n" + "  \"name\": \""+name+"\",\n" + "  \"password\": \""+password+"\"\n"
		  + "}";
		 
		
		}

	@When("^submit post method$")
	public void submit_post_method() throws Throwable {
		res = RestAssured.given().headers("Content-Type",
				  ContentType.JSON).when().body(body).post("http://localhost:8085/employee");
				   responseBody = res.getBody();
				  System.out.println(responseBody.asString());
	   
	}
	
	@Then("^verify status code of the response$")
	public void verify_status_code_of_the_response() throws Throwable {
	   
		Assert.assertEquals(200, res.getStatusCode());
	}


	@Given("^create request body with input Array$")
	public void create_request_body_with_input_Array() throws Throwable {
		
		  bodyWithArrayInput = "[{\"employeename\":\""+employeename1+"\",\"password\":\""+password1+"\"},{\"employeename\":\""+employeename2+"\",\"password\":\""+password2+"\"}]";
		
			
	}
	
	@When("^submit post method for input array$")
	public void submit_post_method_for_input_array() throws Throwable {
		 res = RestAssured.given().headers("Content-Type",
				  ContentType.JSON).when().body(bodyWithArrayInput).post("http://localhost:8085/employee/createWithArray");
				  ResponseBody responseBody = res.getBody();
				  System.out.println(responseBody.asString());
	}

	@Then("^verify status code of the response input array$")
	public void verify_status_code_of_the_response_input_array() throws Throwable {
		Assert.assertEquals(200, res.getStatusCode());	
	}
	
	@Given("^create request body with List format$")
	public void create_request_body_with_List_format() throws Throwable {
				
			
		  bodyWithListInput = "{\"emp1\": {\"id\": "+id1+",\"name\": \""+name1+"\"},\"emp2\": {\"id\": "+id2+",\"name\": \""+name2+"\"}}";
							
		 
	}

	@When("^submit post method for list body$")
	public void submit_post_method_for_list_body() throws Throwable {
		 res = RestAssured.given().headers("Content-Type",
				  ContentType.JSON).when().body(bodyWithListInput).post("http://localhost:8085/employee/createWithList");
				  ResponseBody responseBody = res.getBody();
				  System.out.println(responseBody.asString());
	}
	
	@Then("^verify status code of the response body$")
	public void verify_status_code_of_the_response_body() throws Throwable {
		Assert.assertEquals(200, res.getStatusCode());
	}
}
