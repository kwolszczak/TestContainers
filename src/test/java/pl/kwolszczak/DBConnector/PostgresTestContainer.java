package pl.kwolszczak.DBConnector;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

public class PostgresTestContainer extends DBConnector {

    static PostgreSQLContainer<?> dockerContainer;

    public static PostgreSQLContainer getContainer() {

        dockerContainer = new PostgreSQLContainer<>(DockerImageName.parse(POSTGRES_IMG))
                .withDatabaseName(DB_NAME)
                .withUsername(DB_USER)
                .withPassword(DB_PASSWORD);
        dockerContainer.withStartupTimeout(Duration.ofMinutes(5));
        //org.testcontainers.Testcontainers.exposeHostPorts(DB_PORT_BIND);

        dockerContainer.withCreateContainerCmdModifier(cmd -> {
            cmd.getHostConfig()
                    .withPortBindings(new PortBinding(Ports.Binding.bindPort(DB_PORT_BIND), new ExposedPort(5432)));
        });
        dockerContainer.withInitScript(DB_INIT_SQL);
        // Integer hostPort = dockerContainer.getMappedPort(5432);
        // Integer hostPort2 = dockerContainer.getFirstMappedPort();

        return dockerContainer;
    }
}
