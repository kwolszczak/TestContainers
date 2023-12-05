package pl.kwolszczak;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.kwolszczak.model.Actor;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@MicronautTest(environments = "prod")       //use application-prod.yaml environment  and start DB Postgres on Random port /localhost: RadnomPort/prodapp
class InitTestcontainersTest {


    @Test
    void testItWorks() {

        System.out.println("test     >>>>>>");
        Long id = 3L;
        Actor actorResult =
                given().log().all()
                        .baseUri("http://localhost:8081/prodapp")
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
    void getAllActors() {

        System.out.println("start test  >>>>>>");
        List<Actor> actors =
                given().log().all()
                        .baseUri("http://localhost:8081/prodapp")
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

