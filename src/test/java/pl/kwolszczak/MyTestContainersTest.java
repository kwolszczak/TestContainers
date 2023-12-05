package pl.kwolszczak;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.common.mapper.TypeRef;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.kwolszczak.model.Actor;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@MicronautTest(environments = "test")
//@MicronautTest(transactional = true, environments = "prod", transactionMode = TransactionMode.SEPARATE_TRANSACTIONS)
class MyTestContainersTest {

    @Container
    private static PostgreSQLContainer<?> postgres = DBConnector.getContainerPostgres();

    @Inject
    EmbeddedApplication<?> application;

/*   @MockBean(DBConnector.class)
     DBConnector postgressConnection;*/

    @BeforeAll
    static void setUp() throws InterruptedException {

        System.out.println("start app");
        /* ApplicationContext.run(EmbeddedServer.class, "test", "--port=8080", "--datasources.default.url=" + "jdbc:postgresql://localhost:"+postgres.getMappedPort(5432)+"/actor",
                "--datasources.default.username=" + "postgres",
                "--datasources.default.password=" + "example",
                "--datasources.default.driver-class-name=" + "org.postgresql.Driver");*/

        /*        application = ApplicationContext.builder("test")
               // .exclude("io.micronaut.configuration.hibernate.jpa", "io.micronaut.configuration.jdbc.hikari", "org.postgresql.jdbc")
                .run(EmbeddedApplication.class);*/
    }


    @Test
    void testItWorks() throws InterruptedException {

        System.out.println("test     >>>>>>");
        Long id = 3L;
        Actor actorResult =
                given().log().all()
                        .baseUri("http://localhost:8081/testapp")
                        .basePath("/actors")
                        .pathParam("id", id)
                        .when().get("/{id}")
                        .then().log().all()
                        .statusCode(200)
                        .extract().as(new TypeRef<Actor>() {
                        });

        System.out.println(actorResult);
        String expectedFirstName = "Salma";

        assertThat(actorResult).isNotNull();
        assertThat(actorResult.getFirstname()).isEqualTo(expectedFirstName);
    }

    @Test
    void getAllActors() throws InterruptedException {

        System.out.println("start test  >>>>>>");
        List<Actor> actors =
                given().log().all()
                        .baseUri("http://localhost:8081/testapp")
                        .basePath("/actors")
                        .when().get()
                        .then().log().all()
                        .statusCode(200)
                        .extract().as(new TypeRef<List<Actor>>() {
                        });

        assertThat(actors).isNotNull();
        assertThat(actors.size()).isEqualTo(3);
    }
}

