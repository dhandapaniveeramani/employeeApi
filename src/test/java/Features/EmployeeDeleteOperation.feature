Feature: employee delete operations

Scenario Outline: Verify Invalid employee name supplied in delete operation
	
    Given create request for delete employee operation "<BasicEndPoint>" "<name>" "<password>"
	When perform delete method
	Then verify invalid or not found employee status codes "<statusCode>"
Examples:
    |BasicEndPoint				   | name    |password	|statusCode|
	|http://localhost:8085/employee|employee4|appleflap4|400	   |
	|http://localhost:8085/employee|employee5|appleflap5|404	   |		
