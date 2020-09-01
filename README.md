# Spring Boot Example

This's and simple example Spring Boot test, this contain the necesary files to deploy a Web service application.


### Running the example locally

The example can be run locally using the following Maven goal:

    $ mvn spring-boot:run


You can then access the REST API directly from your Web browser, e.g.:

- <http://localhost:8080/api/clients> Method GET, to Get all
- <http://localhost:8080/api/clients/1> Method GET, to get by id
- <http://localhost:8080/api/clients> Method POST, to add a new client
- <http://localhost:8080/api/clients/1> Method PUT to update a client
