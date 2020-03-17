package com.educational.MovieChallenge.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class Movie {
    @Id
    @GeneratedValue
    private int id;
    @NotNull(message = "Title is mandatory")
    private String title;
    @NotNull(message = "Gender is mandatory")
    private String gender;
    @NotNull(message = "Year is mandatory")
    private Integer year;
    private Integer oscarNumber;
    @NotNull(message = "ActorList is mandatory")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "movie")
    private Set<ActorInMovie> actorList;

    public ActorInMovie addActor(Actor actor) {
        if (actorList == null) {
            actorList = new HashSet<>();
        }
        ActorInMovie movieActor = new ActorInMovie(0,this, actor);
        actorList.add(movieActor);
        return movieActor;
    }

}
