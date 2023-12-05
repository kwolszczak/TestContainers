package pl.kwolszczak;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.Test;
import pl.kwolszczak.model.Actor;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


class RestTest {


    @Test
    void getSingleActorById() {
        Long id = 3L;
        Actor actorResult =
                given().log().all()
                        .baseUri("http://localhost:8081/myapp")
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
        List<Actor> actors =
        given().log().all()
                .baseUri("http://localhost:8081/myapp")
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
