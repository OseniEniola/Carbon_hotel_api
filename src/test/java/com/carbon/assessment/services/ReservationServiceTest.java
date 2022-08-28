package com.carbon.assessment.services;

import com.carbon.assessment.entity.Reservation;
import com.carbon.assessment.error.GlobalException;
import com.carbon.assessment.model.CustomerOverdueStay;
import com.carbon.assessment.repository.ReservationRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;
    @MockBean
    private ReservationRepo reservationRepo;

    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @BeforeEach
    void  setUp(){
        Reservation reservation = Reservation.builder().customerId(12345L)
                .expectedCheckinTime(LocalDateTime.parse("12-12-2020 11:59",format))
                .expectedCheckoutTime(LocalDateTime.parse("01-01-2021 11:00",format))
                .hourlyRate(23000)
                .reservationId(1l)
                .roomType("deluxe")
                .status("paid")
                .build();

        CustomerOverdueStay overdueStay = CustomerOverdueStay.builder()
                .checkOutTime(LocalDateTime.parse("01-01-2021 11:00",format))
                .customerId(12345L)
                .expectedCheckoutTime(LocalDateTime.parse("01-01-2021 11:00",format))
                .overStayAmount(29000)
                .overstayHours(4)
                .roomType("deluxe")
                .build();
        Mockito.when(reservationRepo.findByCustomerId(12345L)).thenReturn(Optional.of(reservation));
    }

    @Test
    public void whenValidCustomerId_thenCalOverstay() throws GlobalException {
        String roomType= "deluxe";
        CustomerOverdueStay found = reservationService.getCustomerOverStay(12345L);
        assertEquals(roomType,found.getRoomType());
    }
}
