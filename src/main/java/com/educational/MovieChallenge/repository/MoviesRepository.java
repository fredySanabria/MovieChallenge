package com.educational.MovieChallenge.repository;

import com.educational.MovieChallenge.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesRepository extends JpaRepository<Movie,Integer> {
}
