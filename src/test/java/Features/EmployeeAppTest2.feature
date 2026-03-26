Feature: employee operation 

#Test Server is up and running
Scenario Outline: Verify employee mock service 

	Given local server is up and running
	Then verify response has successful operation message "<Description>" 
	
	Examples: 
		|Description|
		|Hello, all API tests were successful|
		
#Test login to application for valid user
#Test Log into employee app with invalid user	
Scenario Outline: Log into employee app with valid user
	Given Navigating to URL "<BasicEndPoint>" "<name>" "<password>"
	When submit method
	Then verify status code "<statusCode>"
Examples:
    |BasicEndPoint						 |name    |password	|statusCode|
	|http://localhost:8085/employee/login|dhanda|dhandapass|200	   |
	|http://localhost:8085/employee/login|dhanda|adm	   |400	   |	
	
	
#Test Logout from employee app 	
Scenario Outline: Logout from employee app
	Given Navigate to url to logout "<BasicEndPoint>" "<name>" "<password>"
	When submit method
	Then verify status code "<statusCode>"
Examples:
    |BasicEndPoint						 |name    |password	|statusCode|
	|http://localhost:8085/employee/login|dhanda|dhandapass|200	   |