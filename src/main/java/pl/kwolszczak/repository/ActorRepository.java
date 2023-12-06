package pl.kwolszczak.repository;

import io.micronaut.context.annotation.Parameter;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import pl.kwolszczak.model.Actor;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActorRepository extends CrudRepository<Actor,Long> {

    @Override
    @NonNull List<Actor> findAll();

     @Query(value ="select a from Actor a where a.firstname = :name")
    List<Actor> findByFirstName(String name);

    @Query("delete from Actor a where a.firstname = :firstName" )
    void deleteByName(@NonNull String firstName);

    @Override
    <S extends Actor> @NonNull S save(@NonNull S entity);
}
