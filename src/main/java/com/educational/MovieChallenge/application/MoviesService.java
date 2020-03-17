package com.educational.MovieChallenge.application;

import com.educational.MovieChallenge.DTO.MovieDTO;
import com.educational.MovieChallenge.DTO.MovieMapper;
import com.educational.MovieChallenge.domain.ActorInMovie;
import com.educational.MovieChallenge.domain.Movie;
import com.educational.MovieChallenge.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoviesService {

    @Autowired
    MoviesRepository moviesRepository;

    public Movie save(Movie movie) {
        Movie movieToSave = Movie.builder()
                .title(movie.getTitle())
                .gender(movie.getGender())
                .year(movie.getYear())
                .oscarNumber(movie.getOscarNumber())
                .build();
        movie.getActorList().stream()
                .map(ActorInMovie::getActor)
                .forEach(actor -> movieToSave.addActor(actor));
        return moviesRepository.save(movieToSave);
    }

    public List<MovieDTO> getMovies() {
        return moviesRepository.findAll()
                .stream()
                .map(MovieMapper::mapToMovieDTO)
                .collect(Collectors.toList());
    }
}
