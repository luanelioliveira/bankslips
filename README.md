Bank Slip REST API
===

## Getting Started

### Prerequisites
* Git
* JDK 8 or later
* Maven 3.0 or later

### Clone
To get started you can simply clone this repository using git:
```
git clone https://github.com/luanelioliveira/bankslips.git
cd bankslips
```

### Build an executable JAR

You can run the application from the command line using:

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## API Documentation

```bash
http://localhost:8080/rest/swagger-ui.html
```

## Explore Rest APIs

URL  | HTTP Verb | POST Body | Result 
------------- | ------------- | ------------- | -------------
/rest/bankslips  | GET  | empty  | Return all bankslips
/rest/bankslips  | POST   | JSON string  | New bankslip created
/rest/bankslips/:id  | GET   | empty  | Return detail bankslip
/rest/bankslips/:id  | PUT   | JSON string  | Updates status an existing bankslip

You can test them using postman or any other rest client.

## Testing the API using cURL
Get all bankslips:
```sh
$ curl -i -X GET http://localhost:8080/rest/bankslips 
```
Get bankslip by id:
```sh
$ curl -i -X GET http://localhost:8080/rest/bankslips/55627bfa-4f9e-4dbc-bf1c-bc0c30de5591
```
Add bankslip
```sh
$ curl -i -X POST -H 'Content-Type: application/json' -d '{"due_date": "2018-06-24", "total_in_cents" : 1000, "customer" : "Conta Azul", "status" : "PENDING"}' http://localhost:8080/rest/bankslips
```
Modify status PAID bankslip by id
```sh
$ curl -i -X PUT -H 'Content-Type: application/json' -d '{"status":"PAID"}' http://localhost:8080/rest/bankslips/55627bfa-4f9e-4dbc-bf1c-bc0c30de5591
```
Modify status CANCELED bankslip by id
```sh
$ curl -i -X PUT -H 'Content-Type: application/json' -d '{"status":"CANCELED"}' http://localhost:8080/rest/bankslips/55627bfa-4f9e-4dbc-bf1c-bc0c30de5591
```