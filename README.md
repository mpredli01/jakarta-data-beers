#  Craft Beer Database Application
## An example application using Jakarta Data, Jakarta Data and MongoDB

Contributors:

* Max Arruda
* Michael Redlich
* Otávio Santana

The [Jakarta Data](https://jakarta.ee/specifications/data/) specification provides an API that allows easy access to database technologies. It can split the persistence from the model with several features, such as the ability to compose custom query methods on a `Repository` interface where the framework will implement it.

The [Jakarta NoSQL](https://jakarta.ee/specifications/nosql/) specification defines a set of APIs to provide a standard implementation for most NoSQL databases. Jakarta NoSQL is considered "one API for many NoSQL databases" as it supports the four types of NoSQL databases: column family, document, graph and key-value.

This Jakarta Data, Jakarta NoSQL and MongoDB application that accompanies the presentation, [Jakarta EE 11: Going Beyond the Era of Java EE](https://redlich.net/pdf/portfolio/jakarta-ee-11-going-beyond-the-era-of-java-ee.pdf) to demonstrate how to get started building a MongoDB database application with the Jakarta Data and Jakarta NoSQL specifications.

## Jakarta NoSQL

One of the 42 [specifications](https://jakarta.ee/specifications/) in the Jakarta EE ecosystem that supports all four types of NoSQL databases: column family, document, graph and key-value.

## JNoSQL

The compatible implementation of Jakarta NoSQL.

## MongoDB

![MongoDB Project](http://www.jnosql.org/img/logos/mongodb.png)
   
#### Introduction

MongoDB is a free and open-source cross-platform document-oriented database. Classified as a NoSQL database program, MongoDB uses JSON-like documents with schemas.

To run this project a MongoDB instance is required, so you can use either a local installation or Docker.

#### Manual Installation of MongoDB

Follow the instructions in the [installation guide](https://docs.mongodb.com/manual/installation/).

#### Using Docker

![Docker](https://www.docker.com/sites/default/files/horizontal_large.png)


1. Install docker: https://www.docker.com/
2. https://store.docker.com/images/mongo
3. Run docker command
4. Run MongoDB: verify MongoDB image name with the command `docker images`, it can be mongodb or mongo, and then execute this command:
   1. `docker run -d --name mongodb-instance -p 27017:27017 mongo`

## Clone the Repository

`git clone https://github.com/mpredli01/jakarta-data-beers.git`

## Clean, compile and package the application as a WAR file

`mvn clean compile package`

## Execute the application in Payara Micro

`java -jar payara-micro-6.2024.1.jar /usr/local/apps/jakartaee-apps/jakarta-data-beers/target/beers.war`

## Add a beer to the database

`curl -X POST -H "Content-Type: application/json" -d '{"_id": 3, "name": "Russian Imperial Stout", "type": "STOUT", "brewer_id": 1, "abv": 10.2}' http://10.0.0.229:8080/beers/db/beer/3`

## Add a brewer to the database

`curl -X POST -H "Content-Type: application/json" -d '{"_id": 3, "name": "Maine Beer Compnay", "city": "Freeport", "state": "Maine" }' http://10.0.0.229:8080/beers/db/brewer/{id}`

## Delete a beer from the database

`curl -X DELETE -H http://10.0.0.229:8080/beers/db/beer/{id}`

## Delete a brewer from the database

`curl -X DELETE -H http://10.0.0.229:8080/beers/db/brewer/{id}`

## Resources

* Jakarta Data [specification](https://jakarta.ee/specifications/data/)
* Jakarta NoSQL [specification](https://jakarta.ee/specifications/nosql/)
* JNoSQL [website](http://www.jnosql.org/)
