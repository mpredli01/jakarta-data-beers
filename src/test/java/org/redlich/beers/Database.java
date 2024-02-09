package org.redlich.beers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public enum Database {

    INSTANCE;

    /*
    private final MongoDBContainer mongodb =
            new MongoDBContainer("mongo:latest")
                    .withExposedPorts(27017)
                    .waitingFor(Wait.defaultWaitStrategy());
     */

    /*
    {
        mongodb.start();
    }
     */

    /*
    public MongoClient getMongoClient() {
        return MongoClients.create(mongodb.getConnectionString());
    }

    public String getConnectionString() {
        return mongodb.getHost() + ":" + mongodb.getFirstMappedPort();
    }
     */
}
