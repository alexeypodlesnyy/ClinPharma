# How to use

## Requirements
- Java 8
- Maven 3
- MySQL or Neo4J 4 (in-memory)

## Setup MySQL database
- create new MySQL user
- create <b>new MySQL database</b> and grant an access for created user
- put database name and user credentials to this file:
`src/main/resources/application.properties`

## Build
Go to the project directory and run maven command:

`mvn clean install`

## Run application

### Using MySQL

`java -Dspring.profiles.active=mysql -jar ./target/pharma-server-1.0-SNAPSHOT.jar`

### Using Neo4J

`java -Dspring.profiles.active=neo4j -jar ./target/pharma-server-1.0-SNAPSHOT.jar`

## Test application
1. Request incompatible items:

`localhost:8080/compatibility/check?firstItem=aaa&secondItem=bbb`

Response:

`{
firstItem: "aaa",
secondItem: "bbb",
compatible: false
}`

2. Request compatible items:

`localhost:8080/compatibility/check?firstItem=xxx&secondItem=yyy`

Response:

`{
firstItem: "xxx",
secondItem: "yyy",
compatible: true
}`
