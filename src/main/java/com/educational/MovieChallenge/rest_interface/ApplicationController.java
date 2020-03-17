package com.educational.MovieChallenge.rest_interface;

import com.educational.MovieChallenge.DTO.MovieDTO;
import com.educational.MovieChallenge.application.MoviesService;
import com.educational.MovieChallenge.domain.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class ApplicationController {
    @Autowired
    MoviesService moviesService;

    @PostMapping(path = "/movies")
    public Movie addMovie(@RequestBody Movie newMovie) {
        //newMovie.getActorList().stream()
        return moviesService.save(newMovie);
    }

    @GetMapping(path = "/movies")
    public List<MovieDTO> getMovieList() {
        return moviesService.getMovies();
    }
}
