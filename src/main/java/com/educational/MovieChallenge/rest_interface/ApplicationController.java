package com.educational.MovieChallenge.rest_interface;

import com.educational.MovieChallenge.DTO.MovieDTO;
import com.educational.MovieChallenge.application.MoviesService;
import com.educational.MovieChallenge.domain.Movie;
import com.educational.MovieChallenge.exception.MovieNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class ApplicationController {
    @Autowired
    MoviesService moviesService;

    @PostMapping(path = "/movies")
    public Movie addMovie(@Valid @RequestBody Movie newMovie) {
        return moviesService.save(newMovie);
    }

    @GetMapping(path = "/movies")
    public List<MovieDTO> getMovieList() {
        return moviesService.getMovies();
    }

    @GetMapping(path = "/movies/{id}")
    public Movie getMovie(@PathVariable Integer id) {
        Movie result = moviesService.findMovie(id);
        if (result == null) {
            throw new MovieNotFoundException("id-" + id);
        }
        return result;
    }
}
