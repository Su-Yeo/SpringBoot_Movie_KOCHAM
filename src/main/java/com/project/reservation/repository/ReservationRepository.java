package com.project.reservation.repository;

import com.project.reservation.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Schedule, Long> {

}
