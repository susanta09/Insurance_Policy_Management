# <span style="color:Yellow;font-size:40px">Insurance Management API</span>
<hr style="border:3px solid blue;"> 

#### Index
1. Problem
1. Tools and Resources
1. Models/Entities
1. Entities RelationShip
1. Sample code For Relation
1. DTO and Validation Code Sample
1. File Structure of Project
1. Access the Functionalities
1. Features of Project
1. How to Run Local Server
1. Use Postman(API testing)
1. My Details

### Problem :-
<hr style="border:2px solid green"> 
<span style="color:Yellow">Assignment:-
</span> Create an Insurance Management Platform with  Spring Boot and Java .         

<span style="color:Yellow">Objective:-
</span>
 Build an insurance management platform that allows users to manage insurance
policies, clients, and claims using Spring Boot and Java.

### Tools and Resources:-
<hr style="border:2px solid green">

* Java 8, Spring Web, Spring Data JPA, Rest API, Spring Boot version 2.7.10 .
* Spring Initializr (https://start.spring.io/), STS(Spring Tool Suite), MySQL Server(SQLyog), Postman and Windows .
* Spring-boot Maven Dependencies:-
  * spring-boot-starter-data-jpa
  * spring-boot-starter-validation
  * spring-boot-starter-web
  * mysql-connector-j
  * spring-boot-starter-test
  
### Models/Entities :-
  <hr style="border:2px solid green">
  As par Project requirement we take three Entities which are given below---
  
1. Client Entity
     1. Name (Property)
     1. Date Of Birth (Property)
     1. Address (Property)
     1. contact information (Property)
1. Insurance Policy Entity
    1. policy number (Property)
    1. type (Property)
    1. coverage amount (Property)
    1. premium (Property)
    1. start date (Property)
    1. end date (Property)
1. Claim Entity
    1. claim number (Property) 
    1. description (Property)
    1. claim date (Property)
    1. claim status (Property)

### Entities RelationShip
<hr style="border:2px solid green">
In project i created two relationship----  

1. I build the relationship Many To Many relationship between Claim Entity and Insurance Policy Entity because A Client have many Insurance Policy and Insurance Policy have many Client.
1. And another relationship which is build between  Insurance Policy Entity and Claim Entity.The relationship is Many to One relationship because of a policy can have many claim in different stages.

I showing the image of relationship of entities:--

![relationship](https://user-images.githubusercontent.com/116939650/229948598-623105cc-93a3-4efd-b82f-0bc016ab1836.png)

### Sample code For Relation
<hr style="border:2px solid green">

```java
//==================InsurancePolicy==========
@Entity
public class InsurancePolicy {
	@Id
	@GeneratedValue
	private Integer policy_No;
	private String policy_Type;
	private Double Coverage_Amount;
	private Double premium;
	private String start_Time;
	private String end_Time;
	
	@OneToMany(targetEntity = Claim.class,cascade = CascadeType.REMOVE)
	@JoinColumn(name = "policy_No" )
	private List<Claim> claims;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "policy_addTo_Client",
			joinColumns = {@JoinColumn(name="policy_No")},
			inverseJoinColumns = {@JoinColumn(name="client_No")}
			)
	private List<Client> clients;
}
```
```java
//================== Client =================
@Entity
@Table(name = "client")
public class Client {
	@Id
	@GeneratedValue
	private Long client_No;
	private String name;
	private String dob;
	private String phoneNo;
	@Embedded
	private Address address;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "policy_addTo_Client",
			joinColumns = {@JoinColumn(name="client_No")},
			inverseJoinColumns = {@JoinColumn(name="policy_No")}
			)
	private List<InsurancePolicy> policy;
}
```
```java
//=================== Address ===============
@Embeddable
public class Address {
	private String city;
	private String state;
	private String country;
}
```
```java
//==================== Claim ================
@Entity
public class Claim {
	@Id
	private Integer claim_No;
	private String description;
	private String claim_Date;
	private String claim_Status;
}
```
### DTO and Validation Code Sample
<hr style="border:2px solid green">

```java
//==================== Claim dto ================
public class Claim_dto {
	@NotNull(message = "claim_No your claim it not be null")
	private Integer claim_No;
	@NotNull(message = "Plese describe your claim it not be null")
	private String description;
	@NotNull
	@Pattern(regexp = "^(3[01]|[12][0-9]|00|0[1-9])/(1[0-2]|00|0[1-9])/([0-9]{4})$",message = "date formate dd/mm/yy")
	private String claim_Date;
	@NotNull(message = "status can not be null")
	private String claim_Status;
}
```
```java
//================== Client ================
public class Client_dto {
	private Long client_Id;
	@NotNull(message = "name can not be null")
	@Pattern(regexp = "^[A-Z][a-z]+\\W[A-Z][a-z]+$",
			  message ="First letter of String Must be capital.Take a space between two string")
	private String name;
	@NotNull(message = "date of birth can not be null")
	@Pattern(regexp = "^(3[01]|[12][0-9]|00|0[1-9])/(1[0-2]|00|0[1-9])/([0-9]{4})$",
			  message = "date formate dd/mm/yy")
	private String dob;
	@NotNull(message = "Phone number can not be null")
	@Pattern(regexp = "^(0|91)?[7-9][0-9]{9}$",
			  message ="Enter the valid phone number.first 0 or 91 optional but actual number must be 10 digits" )
	private String phoneNo;
	
	@NotNull(message = "Address can not be null")
	@Valid
	private Address address;
	private List<Integer> policy_No;
}
```

```java
//==================InsurancePolicy==========
public class InsurancePolicy_dto {
	private Integer policy_No;
	@NotEmpty(message ="policy_Type can not be null")
	private String policy_Type;
	@NotNull(message = "Coverage_Amount can not be null")
	private Double Coverage_Amount;
	@NotNull(message = "premium can not be null")
	private Double premium;
	@NotNull
	@Pattern(regexp = "^(3[01]|[12][0-9]|00|0[1-9])/(1[0-2]|00|0[1-9])/([0-9]{4})$",message = "valid date in valid formate dd/mm/yy")
	private String start_Time;
	@NotNull
	@Pattern(regexp = "^(3[01]|[12][0-9]|00|0[1-9])/(1[0-2]|00|0[1-9])/([0-9]{4})$",message = "valid date in valid formate dd/mm/yy")
	private String end_Time;
	private List<Integer> claimsN;
}
```
```java
```

### File Structure of Project
<hr style="border:2px solid green">
Project Folder Structure is given below-----


![Screenshot (411)](https://user-images.githubusercontent.com/116939650/229950999-931caebb-ece2-4c9f-900f-92966382a433.png ) 



![Screenshot (413)](https://user-images.githubusercontent.com/116939650/229951581-f4480421-d601-4970-a903-78d17563f416.png )


### Access the Functionalities
<hr style="border:2px solid green">

* For Client :-

|  Link            | url          |   
|-----------------|---------------|
|POST      |http://localhost:9090/api/clients|
|GET       |http://localhost:9090/api/clients|
|GET       |http://localhost:9090/api/clients/{id}|
|UPDATE    |http://localhost:9090/api/clients/{id}|
|DELETE    |http://localhost:9090/api/clients/{id}|


* For Insurance Policy :-

|  Link            | url          |   
|-----------------|---------------|
|POST      |http://localhost:9090/api/policies|
|GET       |http://localhost:9090/api/policies|
|GET       |http://localhost:9090/api/policies/{id}|
|UPDATE    |http://localhost:9090/api/policies/{id}|
|DELETE    |http://localhost:9090/api/policies/{id}|

* For Claim :-

|  Link            | url                         |   
|-----------------|------------------------------|
|POST      |http://localhost:9090/api/claims|
|GET       |http://localhost:9090/api/claims|
|GET       |http://localhost:9090/api/claims/{id}|
|UPDATE    |http://localhost:9090/api/claims/{id}|
|DELETE    |http://localhost:9090/api/claims/{id}|
#### Features of Project
<hr style="border:2px solid green">

```
The main Features of that project Data
Integrity Exception Handling and Data validation
```

### How to Run Local Server
<hr style="border:2px solid green">

1. Goto my GitHub link.
1. Then goto __code__ Option then click.
1. then click *Download ZIP* for download ZIP file for Project.
1. Create a folder with any name Extract the downloaded ZIP file.
1. Open STS tool and import the file *insurance_policy* 
1. Open Mysql server (SQLyog or MySQL Workbench) And create "apolicy" database.
    ```
    spring.datasource.url=jdbc:mysql://localhost:3306/apolicy
    ```
1. Then goto the project and open resources file .
1. open the application.properties file and chang the username and password as par your mysql server
1. also provide unique port number
    ```
    spring.datasource.username=root
    spring.datasource.password=admin
    server.port=9090
    ```
  __Note:-__
  Problem:
  If get error like-----
  ![error](https://user-images.githubusercontent.com/116939650/230078115-69eb84a7-b4dc-4650-80d5-5cf20b033a4e.png)
  Solution:
    Then you must be add __tomcat-embed-jasper dependency__

```xml
	  <dependency>
        <groupId>org.apache.tomcat.embed</groupId>
        <artifactId>tomcat-embed-jasper</artifactId>
        <scope>provided</scope>
    </dependency>
```


### Use Postman(API testing)
<hr style="border:2px solid green">

 Here i provide structure of entities in Json formate for working in Postman tool.  

*  Json Client :-
```json
{
    "client_Id": 3,
    "name": "Susanta Barman",
    "dob": "23/07/2009",
    "phoneNo": "8768316572",
    "address": {
        "city": "herosima",
        "state": "cocoham",
        "country": "japan"
    },
    "policy_No": []
}
```
* json for Insurance policy :-
```json
 {
        "policy_No": 1,
        "policy_Type": "type1",
        "premium": 3000,
        "start_Time": "23/09/2000",
        "end_Time": "21/03/2014",
        "claimsN": [],
        "coverage_Amount": 15000
    }
```
* json for claim
```json 
 {
        "claim_No": 1005,
        "description": "hghghj",
        "claim_Date": "10/08/2005",
        "claim_Status": "not clear"
    }
```

I provide few sample Postman Screenshot images---
![Screenshot (423)](https://user-images.githubusercontent.com/116939650/230072087-d7173cb3-7acf-44b7-a4e2-3b666ae55fff.png)
![Screenshot (424)](https://user-images.githubusercontent.com/116939650/230072101-25e156e8-1357-4ce2-9743-dc3c5d28fbf9.png)
![Screenshot (425)](https://user-images.githubusercontent.com/116939650/230072114-e0d1b8e9-6e5e-4a76-b139-467eb86e8831.png)
![Screenshot (427)](https://user-images.githubusercontent.com/116939650/230072127-80097df6-8e78-417a-bfb9-d0340c339531.png)
![Screenshot (428)](https://user-images.githubusercontent.com/116939650/230072134-17348ade-c25b-4213-9f0d-82ad5813fdae.png)
![Screenshot (429)](https://user-images.githubusercontent.com/116939650/230072154-8aec8ddf-321a-4e45-a5e4-5357e15d0d94.png)
![Screenshot (430)](https://user-images.githubusercontent.com/116939650/230072161-2d8e544e-0c7a-42cf-9daa-a9c487498317.png)
![Screenshot (432)](https://user-images.githubusercontent.com/116939650/230072167-8469b357-68e5-4759-9c23-e9e038150449.png) 


### My Details
<hr style="border:2px solid red">
Name:- Susanta Barman            

Email:- ssbarmant107@gmail.com
Phone No:- 8768316572


