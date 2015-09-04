# Introduction

This Restful Web Application is created by using Dropwizard Framework to handle request of flight searching.

# Running The Application

* Setup flight data for search

		flightDataCsvFilePath: file_location
		flightDataCsvFileType: file_type (remote | local)
		
		Example:
			flightDataCsvFilePath: https://raw.githubusercontent.com/khoaisohd/wego-java-services/master/flight_data.csv
			flightDataCsvFileType: remote

* To test the example application run the following commands.
		
		mvn test
		
* To package the example run.

        mvn package

* To run the server run.

        java -jar target/wego-services-0.9.0-rc5-SNAPSHOT.jar server wego.yml 

* To search flight 

		POST "flights/search"
		
		Request body - XML
			<?xml version="1.0" encoding="UTF-8" ?>
			<root>
				<from>Singapore</from>
				<to>Tokyo</to>
			</root>
			
		Response body
			Not Empty Result
				<?xml version="1.0" encoding="UTF-8" ?>
				<root>	
    				<airlines>Singapore Airlines</airlines>
    				<airlines>Malaysian Airlines</airlines>
    				<airlines>Cathay Pacific</airlines>
    				<airlines>China Air</airlines>
   				</root>	
			Empty Result
				<?xml version="1.0" encoding="UTF-8" ?>
				<error>No airline operating between desired locations</error>
		
# Deploy to Heroku
		heroku create
		git push heroku master
		heroku open