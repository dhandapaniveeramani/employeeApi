Feature: employee create operations

#Test employee create operation after login to employee app
Scenario Outline: Verify employee create operation after login to employee app

	Given create request body
	When submit post method
	Then verify status code of the response
	
	Examples: 
		|Description|
		|successful operation|
#Test creates list of employees with given input array		
Scenario: Creates list of employees with given input array

Given create request body with input Array
When submit post method for input array
Then verify status code of the response input array

#Test creates list of employees with given input
Scenario: Creates list of employees with given input array

Given create request body with List format
When submit post method for list body
Then verify status code of the response body
		
