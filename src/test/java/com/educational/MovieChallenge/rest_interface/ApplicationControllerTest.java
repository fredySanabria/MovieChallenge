package com.educational.MovieChallenge.rest_interface;

import com.educational.MovieChallenge.DTO.MovieDTO;
import com.educational.MovieChallenge.application.MoviesService;
import com.educational.MovieChallenge.domain.Actor;
import com.educational.MovieChallenge.domain.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ApplicationController.class)
public class ApplicationControllerTest {
    @MockBean
    MoviesService moviesService;

    @Autowired
    private MockMvc mockMvc;

    private final Actor testActor = Actor.builder().id(1).name("Keanu Reves").build();
    private final List<Actor> actorList = Arrays.asList(testActor);
    private final Movie testMovie = Movie.builder().id(1)
            .title("Matrix")
            .gender("Acción")
            .oscarNumber(2)
            .year(2002)
            .build();
    private List<MovieDTO> movieDTOS;
    @Before
    public void init(){
        movieDTOS = new ArrayList<>();
        movieDTOS.add(MovieDTO.builder().title("test title").year(2020).build());
        testMovie.addActor(testActor);
    }

    @Test
    public void whenBrowserSendAllMoviesPetitionThenReturnExpectedResult() throws Exception {
        when(moviesService.getMovies()).thenReturn(movieDTOS);
        mockMvc.perform( MockMvcRequestBuilders
                .get("/movies")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("test title")));
    }
    @Test
    public void whenBrowserSendAMoviePetitionThenReturnExpectedResult() throws Exception {
        when(moviesService.findMovie(any())).thenReturn(testMovie);
        mockMvc.perform( MockMvcRequestBuilders
                .get("/movies/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("Matrix")));
    }

    @Test
    public void whenBrowserSendSaveMoviePetitionThenReturnExpectedResult() throws Exception {
        when(moviesService.save(any())).thenReturn(testMovie);
        mockMvc.perform( MockMvcRequestBuilders
                .post("/movies")
                .content(asJsonString(testMovie))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(containsString("Matrix")));
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
