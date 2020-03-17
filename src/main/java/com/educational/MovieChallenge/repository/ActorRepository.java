package com.educational.MovieChallenge.repository;

import com.educational.MovieChallenge.domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor,Integer> {
}
