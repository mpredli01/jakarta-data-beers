package org.redlich.beers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public enum Database {

    INSTANCE;


    public MongoClient getMongoClient() {
        return MongoClients.create(getConnectionString());
    }

    public String getConnectionString() {
        throw new UnsupportedOperationException("to be implemented!");
    }
}
