package pl.kwolszczak.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import pl.kwolszczak.model.Actor;
import pl.kwolszczak.repository.ActorRepository;

import java.util.List;
import java.util.Optional;

@Singleton
public class ActorService {

    @Inject
    ActorRepository actorRepository;

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public List<Actor> getAllActorsByName(String firstName) {
        return actorRepository.findByFirstName(firstName);
    }

    public Optional<Actor> getActorById(Long id) {
        return actorRepository.findById(id);
    }

    public Actor saveActor(Actor actor) {
        return actorRepository.save(actor);
    }

}
