# api-rest-jwt-auth
This is project example to build a simple REST web service with JSON Web Token (JWT) authentication using java spring boot 2

To run this project, make sure you have this:
- IntelliJ Idea or else (optional)
- maven - 3.x.x
- Java - 1.8.x
- Tomcat 8.5
- MySql as datastore (Create table "users")
- Postman (optional)

## Create "users" table inside world db in MySql
```
USE world;
CREATE TABLE users
(
id BIGINT PRIMARY KEY AUTO_INCREMENT, 
name VARCHAR(200), 
age INT, 
fk_city BIGINT, 
username VARCHAR(100), 
password VARCHAR(300)
);
```
Usually world db is already created by MySql

## Data Initialization (Admin Role)
Run this query below
```
INSERT INTO users(id, age, fk_city, name, username, password) 
VALUES(1, 24, 200, 'Admin', 'admin50', '$2a$10$wysmeUeqHgvwCydDNW8QfO9zKS1MX/jfLXKAsj86O9dNkOIgFAfrO')
```

## Modify application.properties
Modify this file in 
```
src/main/resources/application.propeties
```
change several code within that file and adjust with your MySql environment
```
spring.datasource.url=jdbc:mysql://localhost:3306/**your db name here**
spring.datasource.username=**Your MySql username here**
spring.datasource.password=**Your MySql password here**
```
## Build the Project
Build the project before start, open direct cmd inside project folder
```
mvn clean install
```
or you just open the project and execute maven clean install

## Run the Project
```
mvn spring-boot:run
```

## Generate Jwt Token Using Admin Role
Access this link below :
```
Method POST : http://localhost:8080/token/generate-token
Request Header : 
- Content-Type : application/json

Request Body :
{
	"username" : "admin50",
	"password" : "admin500"
}
```
