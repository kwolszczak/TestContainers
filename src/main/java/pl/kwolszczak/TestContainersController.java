package pl.kwolszczak;

import io.micronaut.http.annotation.*;

@Controller("/testContainers")
public class TestContainersController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}