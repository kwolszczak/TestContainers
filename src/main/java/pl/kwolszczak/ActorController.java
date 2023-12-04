package pl.kwolszczak;

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

        @Post
        public HttpResponse<Actor> createActor(@Body Actor actor) {
            Actor savedActor = actorService.saveActor(actor);
            return HttpResponse.created(savedActor);
        }

/*        @Delete("/{id}")
        public HttpResponse<Void> deleteActorById(Long id) {
            actorService.(id);
            return HttpResponse.noContent();
        }*/

}

