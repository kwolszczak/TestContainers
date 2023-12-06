package pl.kwolszczak;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;


@OpenAPIDefinition(
    info = @Info(
            title = "TestContainers",
            version = "0.0",
            description ="My api test",
            license = @License(name = "Apache 2.0", url = "https://actor.bas")
    )
)
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}