Feature: employee update operations
Scenario Outline: Log into employee app with valid user and perform update operation
	
	Given create request for update employee operation "<BasicEndPoint>" "<name>" "<password>"
	When perform put method
	Then verify invalid or not found employee status code "<statusCode>"
Examples:
	|BasicEndPoint				   | name    |password	|statusCode|
	|http://localhost:8085/employee|employee4|appleflap4|400	   |
	|http://localhost:8085/employee|employee5|appleflap5|404	   |	