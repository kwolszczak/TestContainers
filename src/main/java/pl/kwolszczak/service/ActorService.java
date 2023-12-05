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

    public Optional<Actor> getActorById(Long id) {
        return actorRepository.findById(id);
    }

    public List<Actor> getAllActorsByName(String name) {
        return actorRepository.findByFirstName(name);
    }

    public void removeByName(String firstName){
        actorRepository.deleteByName(firstName);
    }

    public Actor saveActor(Actor actor) {
        return actorRepository.save(actor);
    }

}
