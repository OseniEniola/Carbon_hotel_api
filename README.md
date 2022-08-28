
###Build a Service that track overstay fees in a hospitality system

###Overview
    *Your client, hospitality company realizes that a number of it’s guests have fallen into the habit of keeping their bookings occupied beyond their designated checkout time. 
     You database looks like:
     
     reservation_id	room_type	customer_id	hourly_rate	status	expected_checkin_time	expected_checkout_time
     12000	deluxe	12323	230000	paid	2020-12-12 12:00	2021-01-01 11:00
     12001	regular	12324	150000	paid	2020-12-12 12:00	2021-01-01 11:00
     12002	palatial	12100	560000	paid	2020-12-12 12:00	2021-01-01 11:00
     12003	regular	12323	200000	paid	2020-12-25 12:00	2021-01-04 11:00
     
     Build an API that will calculate overstay fees for the customer. 
     1.	Overstay fee should be calculated per hour 
     2.	Overstay is due at the beginning of the hour, for example if a customer’s checkout is due at 12 noon and you checkout at 3:01 pm, you overdue fee is calculated using four hours
     3.	Overdue fees are a percentage of room rates and also differ for week days and weekend rates. There are three types of rooms: regular, deluxe and palatial. Their rates are as follows.
     a.	
     Room type	Week day rates increase	Weekend rates increase
     regular	7%	10%
     deluxe	8.5%	12%
     palatial	11%	16%

### Requirements
   The fully fledged server uses the following:
   
    SpringBoot
    
### Dependencies
    There are a number of third-party dependencies used in the project. Browse the Maven pom.xml file for details of libraries and versions used.

### Building the project
   You will need:
   
    Java JDK 8 or higher
    Maven 3.1.1 or higher
    Git
    
    Clone the project and use Maven to build the server:
    $ mvn clean install
## Swagger URL
    http://localhost:8000/swagger-ui.html#/
    
### Database: H2 Inmemory Database.  
     * Username and password is found on application.yml file
     * Database console: http://localhost/h2-console
    
## Application Test
     You can find and run new test in the test folder   

## Application properties
     * Applicatin port: 8000 (can be canged on the application.yml file)
     * Base url: http://localhost/api/v1
 
### Key Notes/Assupmtion
    * The checkout time is the time at which the API call was made
    * The sample booking table was preloaded into the inmemory db
    * The Rates table was also preloaded into th inmemory db 
