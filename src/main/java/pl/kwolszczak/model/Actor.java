package pl.kwolszczak.model;


import io.micronaut.data.annotation.GeneratedValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="actor")
@Schema(description = "Actor business model")
@Getter @Setter
public class Actor {

    @Id
    @GeneratedValue(value = GeneratedValue.Type.UUID)
    private Long id;

    private String firstname;
    private String lastname;
    private Long rating;

    public Actor() {
    }

    public Actor(Long id, String firstname, String lastname, Long rating) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", rating=" + rating +
                '}';
    }
}
