package com.educational.MovieChallenge.DTO;

import com.educational.MovieChallenge.domain.Movie;

public final class MovieMapper {
    public static MovieDTO mapToMovieDTO(Movie movie){
        return MovieDTO.builder()
                .title(movie.getTitle())
                .year(movie.getYear())
                .build();
    }
}
