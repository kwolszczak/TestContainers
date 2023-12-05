package pl.kwolszczak;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import io.micronaut.runtime.Micronaut;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

public class DBConnector {

    public static PostgreSQLContainer getContainerPostgres() {

        PostgreSQLContainer<?> dockerContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
                .withDatabaseName("actor")
                .withUsername("postgres")
                .withPassword("example")
              ;
        dockerContainer.withStartupTimeout(Duration.ofMinutes(5));
        //org.testcontainers.Testcontainers.exposeHostPorts(15432);

        dockerContainer.withCreateContainerCmdModifier(cmd -> {
            cmd.getHostConfig().withPortBindings(
                    new PortBinding(Ports.Binding.bindPort(15432),
                            new ExposedPort(5432)));
        });
        dockerContainer.withInitScript("init-actor.sql");
        // Integer hostPort = dockerContainer.getMappedPort(5432);
        // Integer hostPort2 = dockerContainer.getFirstMappedPort();


        return dockerContainer;
    }
}
