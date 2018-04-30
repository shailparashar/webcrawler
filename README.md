# Web Crawler
This program is a deep web crawler to go upto provided depth. Default depth of 2 is configured in case depth param is not provided in request. 
It is exposed as a REST endpoint and provides additional below features:
- It uses caching mechanism to improve performance for repeated urls. 
- It ignores similar child urls to prevent looping.
- It provides security from cross-site scripting attacks.

## Implementation
The solution delivered here is a Java project implemented as a Spring Boot / Maven project.

Tech/Tools used to develop this solution
- Springboot
- Spring Web
- Jsoup library for fetching web content and parsing HTML
- Mockito for unit testing


## Building the program
In order to build the program, the following is required

- Java 8 JDK
- Maven 3.x.x

### Building project and running test cases locally:
project root: mvn install

### Running project locally:
project root: mvn spring-boot:run

After running application service will be available at 
http://localhost:8080/webcrawler?url={url}&maxdepth={maxDepth}

Example for a quick test over PostMan or any rest client:
http://localhost:8080/webcrawler?url=http://www.google.com&maxdepth=1


## Additional Notes

### Request Params
Param Name : url
Data Type : String
Availability : Mandatory

Param Name : maxdepth
Data Type : String
Availability : Optional, default value is 5 if not provided in request


### Response Param

#### Success Response :
ResponseEntity of type Node Object
Node Object:
url : String
Title : String
Nodes : Set

#### Error Response :
ResponseEntity of type WebCrawlerError Object
WebCrawlerError Object:
code : HttpStatus
message : String
