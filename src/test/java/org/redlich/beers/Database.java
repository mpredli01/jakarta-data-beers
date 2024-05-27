package org.redlich.beers;

import de.flapdoodle.embed.mongo.commands.ServerAddress;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.mongo.transitions.Mongod;
import de.flapdoodle.embed.mongo.transitions.RunningMongodProcess;
import de.flapdoodle.embed.process.io.ProcessOutput;
import de.flapdoodle.reverse.TransitionWalker;
import de.flapdoodle.reverse.transitions.Start;

public enum Database {

    INSTANCE;

    private final TransitionWalker.ReachedState<RunningMongodProcess> mongoRunningProcess;

    Database() {
        this.mongoRunningProcess = Mongod
                .instance()
                // supressing the mongodb process output
                .withProcessOutput(Start.to(ProcessOutput.class)
                    .initializedWith(ProcessOutput.silent())
                    .withTransitionLabel("no output"))
                .start(Version.Main.V7_0);
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
