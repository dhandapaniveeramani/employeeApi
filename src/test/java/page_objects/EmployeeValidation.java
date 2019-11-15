package page_objects;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;
import org.junit.Assert;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;


public class EmployeeValidation  {
	static WireMockServer wireMockServer;
	StubMapping foo=null;
	public static WireMockServer serverObject()
	{
		wireMockServer = new WireMockServer(wireMockConfig().port(8085)); 
		 
		 return wireMockServer;
	}
public void startServer()
{
	serverObject();
	 wireMockServer.start();
	 WireMock.configureFor("localhost", 8085);
	 
	 foo =stubFor(get(urlMatching("/employee")).willReturn(aResponse().withStatus(200)
			  . withBody("Hello, all API tests were successful")));
			  System.out.println("Server started successfully ..");
			  wireMockServer.addStubMapping(foo);
			 
			// login employee stub --200
				
				StubMapping foo1 = null;
				foo1 = stubFor(get(urlMatching("/employee/login\\?employeename=dhanda&password=dhandapass")).willReturn(aResponse().withStatus(200).withBody("successfull operatoin")));
				wireMockServer.addStubMapping(foo1);
				
				// invalid login employee stub --400
				StubMapping foo2 = null;
				foo2 = stubFor(get(urlMatching("/employee/login\\?employeename=dhanda&password=adm")).willReturn(aResponse().withStatus(400).withBody("Invalid employeename/password supplied")));
				wireMockServer.addStubMapping(foo2);
				
				
				// create employee stub --200
				String password="appleflap1";
				String name="employee1";
				String body = 

								"{ \n" + "  \"name\": \""+name+"\",\n" + "  \"password\": \""+password+"\"\n"
								+ "}";
				StubMapping foo3 = null;
				foo3 = stubFor(post(urlMatching("/employee")).withRequestBody(equalToJson(body)).willReturn(aResponse().withStatus(200).withBody("successful operation")));
				wireMockServer.addStubMapping(foo3);
				
				//create employee with array -200
				
				 String password1="appleflap1";
				  String employeename1="employee1";
				  String password2="appleflap2";
				  String employeename2="employee2";
				  	  
				  String bodyWithArrayInput = "[{\"employeename\":\""+employeename1+"\",\"password\":\""+password1+"\"},{\"employeename\":\""+employeename2+"\",\"password\":\""+password2+"\"}]";
				  StubMapping arrayInput=null;
				  arrayInput=stubFor(post(urlMatching("/employee/createWithArray")).withRequestBody(equalToJson(bodyWithArrayInput)).willReturn(aResponse().withStatus(200).withBody("successful operation")));
				  wireMockServer.addStubMapping(arrayInput);
				  
				  
				  //create employee with list -200
				  
				  
				  int id1=100;
				  String name1="employee1";
				  int id2=200;
				  String name2="employee2";
					
					
				  String bodyWithListInput = "{\"emp1\": {\"id\": "+id1+",\"name\": \""+name1+"\"},\"emp2\": {\"id\": "+id2+",\"name\": \""+name2+"\"}}";
				  StubMapping listInput=null;
				  listInput=stubFor(post(urlMatching("/employee/createWithList")).withRequestBody(equalToJson(bodyWithListInput)).willReturn(aResponse().withStatus(200).withBody("successful operation")));
				  wireMockServer.addStubMapping(listInput);
				  
				  //logout employee
				  
				  
				  foo = stubFor(get(urlMatching("/employee/logout\\?employeename=dhanda&password=dhandapass")).willReturn(aResponse().withStatus(200).withBody("successfull operatoin")));
					wireMockServer.addStubMapping(foo);
					
					
					//get employee by name
					
					 foo = stubFor(get(urlMatching("/employee/employee1")).willReturn(aResponse().withStatus(200).withBody(body)));
						wireMockServer.addStubMapping(foo);
						
					foo = stubFor(get(urlMatching("/employee/nmp")).willReturn(aResponse().withStatus(400).withBody("Invalid employeename supplied")));
						wireMockServer.addStubMapping(foo);
						
					//put employee
					
						password="appleflap4";
						name="employee4";
						body = 

										"{ \n" + "  \"name\": \""+name+"\",\n" + "  \"password\": \""+password+"\"\n"
										+ "}";
						
						foo = stubFor(put(urlMatching("/employee")).withRequestBody(equalToJson(body)).willReturn(aResponse().withStatus(400).withBody("Invalid employeename supplied")));
						wireMockServer.addStubMapping(foo);
						
						
					//delete employee
					
						password="appleflap4";
						name="employee4";
						body = 

										"{ \n" + "  \"name\": \""+name+"\",\n" + "  \"password\": \""+password+"\"\n"
										+ "}";
						
						foo = stubFor(delete(urlMatching("/employee")).withRequestBody(equalToJson(body)).willReturn(aResponse().withStatus(400).withBody("Invalid employeename supplied")));
						wireMockServer.addStubMapping(foo);
					
}	

	/*
	 * Employee Operations
	 * 
	 */
	
	public void serverStartup() throws ClientProtocolException, IOException
	{
		
						
		Response res = RestAssured.given().headers("Content-Type", ContentType.JSON).when().get("http://localhost:8085/employeeApp");
		ResponseBody responseBody = res.getBody(); 
		System.out.println(responseBody.asString());
		  
	
	}
	
	/*
	 *  operationId: "loginemployee"
	 *  
	 */
			
	
			
	public void testLoginEmployee() throws Exception
	{
			
		
		  Response res = RestAssured.given().headers("Content-Type",
		  ContentType.JSON).when() .queryParam("employeename", "dhanda")
		  .queryParam("password","dhandapass").get("http://localhost:8085/employee/login"); 
		  
		  ResponseBody responseBody = res.getBody();
		  System.out.println(responseBody.asString());
		  int response_code=res.getStatusCode();
		  Assert.assertEquals(200, response_code);
	}
	
	
	public void testInvalidLoginEmployee() throws Exception
	{

		Response res1 = RestAssured.given().headers("Content-Type", ContentType.JSON).when()
				.queryParam("employeename", "dhanda").queryParam("password", "adm")
				.get("http://localhost:8085/employee/login");
		ResponseBody responseBody1 = res1.getBody();
		System.out.println(responseBody1.asString());
		int response_code = res1.getStatusCode();
		Assert.assertEquals(400, response_code);

	}	
	
	/*
	 * operationId: "createemployee"
	 * 
	 */
	
	public void createEmployee()
	{
		  String password="appleflap1";
			String name="employee1";
			String body = 

							"{ \n" + "  \"name\": \""+name+"\",\n" + "  \"password\": \""+password+"\"\n"
							+ "}";
		  Response res2 = RestAssured.given().headers("Content-Type",
				  ContentType.JSON).when().body(body).post("http://localhost:8085/employee");
				  ResponseBody responseBody2 = res2.getBody();
				  System.out.println(responseBody2.asString());
			Assert.assertEquals(200, res2.getStatusCode());	
		
	}

	public void createEmployeeWithArray()
	{
		  String password1="appleflap1";
		  String employeename1="employee1";
		  String password2="appleflap2";
		  String employeename2="employee2";
			
			
		  String bodyWithArrayInput = "[{\"employeename\":\""+employeename1+"\",\"password\":\""+password1+"\"},{\"employeename\":\""+employeename2+"\",\"password\":\""+password2+"\"}]";
							
		  Response res2 = RestAssured.given().headers("Content-Type",
				  ContentType.JSON).when().body(bodyWithArrayInput).post("http://localhost:8085/employee");
				  ResponseBody responseBody2 = res2.getBody();
				  System.out.println(responseBody2.asString());
			Assert.assertEquals(200, res2.getStatusCode());	
		
	}
	
	public void createEmployeeWithList()
	{
		  int id1=100;
		  String name1="employee1";
		  int id2=200;
		  String name2="employee2";
			
			
		  String bodyWithListInput = "{\"emp1\": {\"id\": "+id1+",\"name\": \""+name1+"\"},\"emp2\": {\"id\": "+id2+",\"name\": \""+name2+"\"}}";
							
		  Response res2 = RestAssured.given().headers("Content-Type",
				  ContentType.JSON).when().body(bodyWithListInput).post("http://localhost:8085/employee");
				  ResponseBody responseBody2 = res2.getBody();
				  System.out.println(responseBody2.asString());
			Assert.assertEquals(200, res2.getStatusCode());	
		
	}
	//operationId: "logoutemployee"

	public void testLogoutEmployee() throws Exception
	{
		RestAssured.baseURI="http://localhost:8080/employeeApp";
		JSONObject obj=new JSONObject();
		RequestSpecification req=RestAssured.given();
		req.header("Content-Type","application/json");
		req.body(obj.toString());
		Response res=req.get("/employee/logout");
		int response_code=res.getStatusCode();
					
		Assert.assertEquals(200, response_code);

	}

	//operationId: "getemployeeByName"

	public void testGetEmployeeByName(String empoyeename) throws Exception
	{
		EmployeeValidation Employee=new EmployeeValidation();
		RestAssured.baseURI="http://localhost:8080/employeeApp";
		JSONObject obj=new JSONObject();
		RequestSpecification req=RestAssured.given();
		obj.put("employeename","employeeAA");
		
		req.header("Content-Type","application/json");
		req.body(obj.toString());
		Response res=req.get("/employee/"+empoyeename);
		int response_code=res.getStatusCode();
					
		Assert.assertEquals(200, response_code);
		Employee =res.jsonPath().get("employeename");
		
		String actualEmployeeName = Employee.getName();
		Assert.assertEquals(empoyeename, actualEmployeeName);

	}

	public String getName()
	{
		return "";
	}

	public void testGetInvalidEmployee(String empoyeename) throws Exception
	{
		RestAssured.baseURI="http://localhost:8080/employeeApp";
		JSONObject obj=new JSONObject();
		RequestSpecification req=RestAssured.given();
		obj.put("employeename","employeeAA");
		
		req.header("Content-Type","application/json");
		req.body(obj.toString());
		Response res=req.get("/employee/"+empoyeename);
		int response_code=res.getStatusCode();
					
		Assert.assertEquals(400, response_code);
		

	}

	public void testEmplyeeNotFound(String empoyeename) throws Exception
	{
		RestAssured.baseURI="http://localhost:8080/employeeApp";
		JSONObject obj=new JSONObject();
		RequestSpecification req=RestAssured.given();
		obj.put("employeename","employeeAA");
		
		req.header("Content-Type","application/json");
		req.body(obj.toString());
		Response res=req.get("/employee/"+empoyeename);
		int response_code=res.getStatusCode();
					
		Assert.assertEquals(404, response_code);
		

	}

	// operationId: "updateemployee"


	public void testUpdateEmployee(Integer id,String empoyeename, String firstName, String lastName, String email, String password,String phone, String employeeStatus ) throws Exception
	    
	{
		
		RestAssured.baseURI="http://localhost:8080/employeeApp";
		/*
		 * String request = "{\n" +
		 * 
		 * "  \"directoryPath\": \""+empoyeename+"\",\n" +
		 * "  \"fileName\": \""+fileName+"\"\n" + "}";
		 */
		
		String request = "{\n" +

						"  \"id\": \""+id+"\",\n" + "  \"empoyeename\": \""+empoyeename+"\"\n" +"\"firstName \":\""+firstName+"\"\n"+"\"lastName \":\""+lastName+"\"\n"+
						"\"email \":\""+email+"\"\n" + "\"password \":\""+password+"\"\n"+ "\"phone \":\""+phone+"\"\n"+"\"employeeStatus \":\""+employeeStatus+"\"\n"+"}";
		Response response=null;
		try
		{
			response=RestAssured.given().contentType(ContentType.JSON).body(request).put("/employee/"+empoyeename);
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		int statusCode=response.getStatusCode();
		Assert.assertEquals(200, statusCode);
		Assert.assertTrue("verifying employee name exists in the response", response.asString().contains(empoyeename));
	}
	
	public void testInvalidUpdateEmployee(Integer id,String empoyeename, String firstName, String lastName, String email, String password,String phone, String employeeStatus ) throws Exception
    
	{
		
		RestAssured.baseURI="http://localhost:8080/employeeApp";
		/*
		 * String request = "{\n" +
		 * 
		 * "  \"directoryPath\": \""+empoyeename+"\",\n" +
		 * "  \"fileName\": \""+fileName+"\"\n" + "}";
		 */
		
		String request = "{\n" +

						"  \"id\": \""+id+"\",\n" + "  \"empoyeename\": \""+empoyeename+"\"\n" +"\"firstName \":\""+firstName+"\"\n"+"\"lastName \":\""+lastName+"\"\n"+
						"\"email \":\""+email+"\"\n" + "\"password \":\""+password+"\"\n"+ "\"phone \":\""+phone+"\"\n"+"\"employeeStatus \":\""+employeeStatus+"\"\n"+"}";
		Response response=null;
		try
		{
			response=RestAssured.given().contentType(ContentType.JSON).body(request).put("/employee/"+"invalid employee");
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		int statusCode=response.getStatusCode();
		Assert.assertEquals(400, statusCode);
		
	}
	
	
public void testEmployeeNotFoundInUpdateEmployee(Integer id,String empoyeename, String firstName, String lastName, String email, String password,String phone, String employeeStatus ) throws Exception
    
	{
		
		RestAssured.baseURI="http://localhost:8080/employeeApp";
		/*
		 * String request = "{\n" +
		 * 
		 * "  \"directoryPath\": \""+empoyeename+"\",\n" +
		 * "  \"fileName\": \""+fileName+"\"\n" + "}";
		 */
		
		String request = "{\n" +

						"  \"id\": \""+id+"\",\n" + "  \"empoyeename\": \""+empoyeename+"\"\n" +"\"firstName \":\""+firstName+"\"\n"+"\"lastName \":\""+lastName+"\"\n"+
						"\"email \":\""+email+"\"\n" + "\"password \":\""+password+"\"\n"+ "\"phone \":\""+phone+"\"\n"+"\"employeeStatus \":\""+employeeStatus+"\"\n"+"}";
		Response response=null;
		try
		{
			response=RestAssured.given().contentType(ContentType.JSON).body(request).put("/employee/"+"notFoundEmployee");
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		int statusCode=response.getStatusCode();
		Assert.assertEquals(404, statusCode);
		
	}

//operationId: "deleteemployee"

public void deleteEmployee(String employeename)
{
	RestAssured.baseURI="http://localhost:8080/employeeApp";
	Response response=null;
	try
	{
		response=RestAssured.given().contentType(ContentType.JSON).delete("/employee/"+employeename);
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	
	Assert.assertEquals(200, response.getStatusCode());
}

public void deleteInvalidEmployee(String employeename)
{
	RestAssured.baseURI="http://localhost:8080/employeeApp";
	Response response=null;
	try
	{
		response=RestAssured.given().contentType(ContentType.JSON).delete("/employee/"+employeename);
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	
	Assert.assertEquals(400, response.getStatusCode());
}

public void deleteNotFoundEmployee(String employeename)
{
	RestAssured.baseURI="http://localhost:8080/employeeApp";
	Response response=null;
	try
	{
		response=RestAssured.given().contentType(ContentType.JSON).delete("/employee/"+employeename);
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	
	Assert.assertEquals(404, response.getStatusCode());
}

//operationId: "createemployee"

public void createEmployee(Integer id,String empoyeename, String firstName, String lastName, String email, String password,String phone, String employeeStatus ) throws Exception
{
	RestAssured.baseURI="http://localhost:8080/employeeApp";
		
	String request = "{\n" +

					"  \"id\": \""+id+"\",\n" + "  \"empoyeename\": \""+empoyeename+"\"\n" +"\"firstName \":\""+firstName+"\"\n"+"\"lastName \":\""+lastName+"\"\n"+
					"\"email \":\""+email+"\"\n" + "\"password \":\""+password+"\"\n"+ "\"phone \":\""+phone+"\"\n"+"\"employeeStatus \":\""+employeeStatus+"\"\n"+"}";
	Response response=null;
	try
	{
		response=RestAssured.given().contentType(ContentType.JSON).body(request).post("/employee"+empoyeename);
		
	
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	int statusCode=response.getStatusCode();
	Assert.assertEquals(200, statusCode);
	System.out.println("employee response"+ response.asString());
	System.out.println("employee name is " +response.jsonPath().get("empoyeename"));
	
}
	public void stopServer()
	{
		
		WireMockServer server=EmployeeValidation.serverObject();
		server.stop();
	}
}

