package com.carbon.assessment.repository;

import com.carbon.assessment.entity.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReservationRepoTest {
    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private TestEntityManager testEntityManager;
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @BeforeEach
    void setUp(){
       Reservation reservation = Reservation.builder().customerId(12345L)
                .expectedCheckinTime(LocalDateTime.parse("12-12-2020 11:59",format))
                .expectedCheckoutTime(LocalDateTime.parse("01-01-2021 11:00",format))
                .hourlyRate(23000)
                .reservationId(1l)
                .roomType("deluxe")
                .status("paid")
                .build();

        testEntityManager.persist(reservation);
    }

    @Test
    public void whenFindCustomerId_thenReturnReservation(){
        Reservation reservation = reservationRepo.findByCustomerId(12345L).get();
        assertEquals(reservation.getCustomerId(),12345L);
    }
}
