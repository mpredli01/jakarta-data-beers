#  Craft Beer Database Application
## Getting Started with Jakarta NoSQL and MongoDB

The [Jakarta NoSQL](https://jakarta.ee/specifications/nosql/) specification defines a set of APIs to provide a standard implementation for most NoSQL databases. Jakarta NoSQL is considered "one API for many NoSQL databases" as it supports the four types of NoSQL databases: column family, document, graph and key-value.

This presentation will provide an introduction to Jakarta NoSQL and [Eclipse JNoSQL](https://projects.eclipse.org/projects/technology.jnosql), the compatible implementation to the specification, followed by a demonstration of a MongoDB application built with Jakarta NoSQL.

This Jakarta NoSQL and MongoDB application that accompanies the presentation, [Getting Started with Jakarta NoSQL and MongoDB](https://redlich.net/pdf/portfolio/getting-started-with-jakarta-nosql-and-mongodb.pdf) to demonstrate how to get started building a MongoDB database application with the Jakarta NoSQL specification.

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

`git clone https://github.com/mpredli01/jnosql-beers.git`

## Clean, compile and execute the application

`mvn clean compile`

`mvn exec:java -Dexec.mainClass=org.redlich.beers.BeerApp`

## Resources

* Jakarta NoSQL [specification](https://jakarta.ee/specifications/nosql/)
* JNoSQL [website](http://www.jnosql.org/)
* `jnosql-beers` GitHub [repository](https://github.com/mpredli01/jnosql-beers)
* Getting Started with Jakarta NoSQL and MongoDB [slide deck](https://redlich.net/pdf/portfolio/getting-started-with-jakarta-nosql-and-mongodb.pdf)