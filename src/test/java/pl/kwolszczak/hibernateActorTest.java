package pl.kwolszczak;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.kwolszczak.containers.PostgresContainer;
import pl.kwolszczak.model.Actor;
import pl.kwolszczak.repository.ActorRepository;

@Testcontainers
@MicronautTest(environments = "test", transactional = false) //transactiona -false and you can write to db after every signle test
class hibernateActorTest {

    @Container
    private static PostgreSQLContainer<?> postgres = PostgresContainer.getContainer();

    @Inject
    ActorRepository actorRepository;


    @Test
    void writeToDBdirectly_test() {
        actorRepository.save(new Actor(12L, "Tola", "Holek", 12L));
        actorRepository.findAll().forEach(System.out::println);
    }


}

