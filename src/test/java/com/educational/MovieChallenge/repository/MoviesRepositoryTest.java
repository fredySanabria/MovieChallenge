package com.educational.MovieChallenge.repository;

import com.educational.MovieChallenge.domain.Actor;
import com.educational.MovieChallenge.domain.ActorInMovie;
import com.educational.MovieChallenge.domain.Movie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoviesRepositoryTest {

    @Autowired
    private MoviesRepository moviesRepository;
    @Autowired
    private ActorRepository actorRepository;

    private final Actor testActor = Actor.builder().id(1).name("Keanu Reves").build();
    private final Movie testMovie = Movie.builder().id(1)
            .title("Matrix")
            .gender("Acción")
            .oscarNumber(2)
            .year(2002)
            .build();
    @Before
    public void init(){
        actorRepository.save(testActor);
        testMovie.addActor(testActor);;
    }



    @Test
    public void whenServiceSendSaveActionThenReturnExpectedResult(){
        Movie result = moviesRepository.save(testMovie);
        assertThat(result.getTitle()).isEqualTo("Matrix");
    }
}
