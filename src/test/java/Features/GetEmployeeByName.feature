Feature: employee enquiry operations

#Test employee create operation after login to employee app
Scenario Outline: Verify get employee by name in employee app

	Given create get employee by name request body "<BasicEndPoint>" "<name>"
	When submit get method
	Then verify status code for get employee by name "<StatusCode>"
Examples:
	|BasicEndPoint				   | name    |StatusCode|
	|http://localhost:8085/employee|/nmp	  |400	   |
	|http://localhost:8085/employee|/employee2|404	   |
	|http://localhost:8085/employee|/employee1|200	   |
		

	
	
