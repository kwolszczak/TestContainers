package pl.kwolszczak.containers;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

public class PostgresContainer {

    private static final String DB_NAME = "actor";
    private static final String DB_USER= "postgres";
    private static final String DB_PASSWORD = "example";
    private static final String DB_INIT_SQL = "init-actor.sql";
    private static final int DB_PORT_BIND = 15432;
    private static final String POSTGRES_IMG = "postgres:latest";

    public static PostgreSQLContainer getContainer() {

        PostgreSQLContainer<?> dockerContainer = new PostgreSQLContainer<>(DockerImageName.parse(POSTGRES_IMG))
                .withDatabaseName(DB_NAME)
                .withUsername(DB_USER)
                .withPassword(DB_PASSWORD)
              ;
        dockerContainer.withStartupTimeout(Duration.ofMinutes(5));
        //org.testcontainers.Testcontainers.exposeHostPorts(DB_PORT_BIND);

        dockerContainer.withCreateContainerCmdModifier(cmd -> {
            cmd.getHostConfig().withPortBindings(
                    new PortBinding(Ports.Binding.bindPort(DB_PORT_BIND),
                            new ExposedPort(5432)));
        });
        dockerContainer.withInitScript(DB_INIT_SQL);
        // Integer hostPort = dockerContainer.getMappedPort(5432);
        // Integer hostPort2 = dockerContainer.getFirstMappedPort();


        return dockerContainer;
    }
}
