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

<<<<<<< HEAD
    /*
    private final MongoDBContainer mongodb =
            new MongoDBContainer("mongo:latest")
                    .withExposedPorts(27017)
                    .waitingFor(Wait.defaultWaitStrategy());
     */

    /*
    {
        mongodb.start();
=======

    private final TransitionWalker.ReachedState<RunningMongodProcess> mongoRunningProcess;

    Database() {
        this.mongoRunningProcess = Mongod
                .instance()
                .start(Version.Main.V7_0);
>>>>>>> 3208852f6bc09f4c07165c5c65df166e7bc57f35
    }
     */

<<<<<<< HEAD
    /*
=======

>>>>>>> 3208852f6bc09f4c07165c5c65df166e7bc57f35
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
     */
}
