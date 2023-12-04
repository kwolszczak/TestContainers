package pl.kwolszczak.repository;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import pl.kwolszczak.model.Actor;

import java.util.List;

@Repository
public interface ActorRepository extends CrudRepository<Actor,Long> {

    @Override
    @NonNull List<Actor> findAll();

     @Query(value ="select * from actor s where s.firstname - :firstName")
    List<Actor> findByFirstName(String firstName);
}
