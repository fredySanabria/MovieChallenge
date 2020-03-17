package com.educational.MovieChallenge.application;

import com.educational.MovieChallenge.DTO.MovieDTO;
import com.educational.MovieChallenge.domain.Actor;
import com.educational.MovieChallenge.domain.Movie;
import com.educational.MovieChallenge.repository.MoviesRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MoviesServiceTest {
    @Mock
    MoviesRepository moviesRepository;

    @InjectMocks
    private MoviesService moviesService;

    private final Actor testActor = Actor.builder().id(1).name("Keanu Reves").build();
    private final Movie testMovie = Movie.builder().id(1)
            .title("Matrix")
            .gender("Acción")
            .oscarNumber(2)
            .year(2002)
            .build();


    @Test
    public void whenControllerSendSaveActionThenReturnExpectedResult(){
        testMovie.addActor(testActor);
        when(moviesRepository.save(any())).thenReturn(testMovie);
        Movie result = moviesService.save(testMovie);
        assertThat(result.getId()).isEqualTo(1);
    }

    @Test
    public void whenControllerSendAllMoviesActionThenReturnExpectedResult(){
        testMovie.addActor(testActor);
        when(moviesRepository.findAll()).thenReturn(Arrays.asList(testMovie));
        List<MovieDTO> result = moviesService.getMovies();
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void whenControllerSendAMovieActionThenReturnExpectedResult(){
        testMovie.addActor(testActor);
        when(moviesRepository.getOne(any())).thenReturn(testMovie);
        Movie result = moviesService.findMovie(1);
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Matrix");
    }
}
