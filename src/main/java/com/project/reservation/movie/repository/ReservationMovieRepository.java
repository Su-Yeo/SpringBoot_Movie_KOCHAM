package com.project.reservation.movie.repository;

import com.project.reservation.movie.entity.ReservationMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationMovieRepository extends JpaRepository<ReservationMovie, Long> {
}
