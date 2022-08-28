package com.carbon.assessment.repository;

import com.carbon.assessment.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation,Long> {

   Optional<Reservation> findByCustomerId(Long id);
}
