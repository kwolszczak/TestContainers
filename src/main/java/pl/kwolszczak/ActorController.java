package pl.kwolszczak;

import io.micronaut.data.annotation.Query;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import pl.kwolszczak.model.Actor;
import pl.kwolszczak.service.ActorService;

import java.util.List;


@Controller("/actors")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @Get
    public List<Actor> getAllActors() {
        return actorService.getAllActors();
    }

    @Get("/{id}")
    public HttpResponse<Actor> getActorById(Long id) {
        return actorService.getActorById(id)
                .map(HttpResponse::ok)
                .orElseGet(() -> HttpResponse.notFound());
    }

    @Get("/name/{name}")
    public List<Actor> getAllactorsByName(String name) {
        return actorService.getAllActorsByName(name);
    }

    @Post
    public HttpResponse<Actor> createActor(@Body Actor actor) {
        Actor savedActor = actorService.saveActor(actor);
        return HttpResponse.created(savedActor);
    }

        @Delete("/{name}")
        public HttpResponse<Void> delete(String name) {
            actorService.removeByName(name);
            return HttpResponse.noContent();
        }

}

