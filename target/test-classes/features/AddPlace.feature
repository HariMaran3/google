Feature: Adding and Deleting Places in GMaps

Scenario Outline: AddPlace

	Given Adding payload having "<latitude>" "<longitude>" "<name>" "<address>"
	When posting payload in "addPlaceAPI" using "Post" method
	Then gives statusCode of 200 on successful execution
	And "scope" equals to "APP"
	And "status" equals to "OK" 
	
	
	Examples:
	
	|latitude |longitude | name 		| address |
	|12.33	  |23.25	 | Andhra mess	|Chennai  |
	|12.34	  |23.26	 | Karnat mess	|Chennai  |
	
	
Scenario: GetPlace 

	Given Add place Id
	When posting payload in "getPlaceAPI" using "get" method
	Then gives statusCode of 200 on successful execution
	
Scenario: DeletePlace 

	Given Adding place Id in payload
	When posting payload in "deletePlaceAPI" using "delete" method
	Then gives statusCode of 200 on successful execution
	