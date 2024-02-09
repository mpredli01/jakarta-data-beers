package org.redlich.beers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.commands.ServerAddress;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.mongo.transitions.Mongod;
import de.flapdoodle.embed.mongo.transitions.RunningMongodProcess;
import de.flapdoodle.reverse.TransitionWalker;

public enum Database {

    INSTANCE;

    public MongoClient getMongoClient() {
        return MongoClients.create(getConnectionString());
    }

    public String getConnectionString() {
        return connectionStringOf(mongoRunningProcess.current().getServerAddress());
    }

    private String connectionStringOf(ServerAddress serverAddress) {
        return "%s:%s".formatted(
                serverAddress.getHost(),
                serverAddress.getPort());
    }
}
