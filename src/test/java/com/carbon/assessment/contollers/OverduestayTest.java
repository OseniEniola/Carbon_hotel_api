package com.carbon.assessment.contollers;

import com.carbon.assessment.entity.Reservation;
import com.carbon.assessment.model.CustomerOverdueStay;
import com.carbon.assessment.services.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(Overduestay.class)
class OverduestayTest {

    @Autowired
    private MockMvc webMvcTest;

    @MockBean
    private ReservationService reservationService;

    private Reservation reservation;
    private CustomerOverdueStay overdueStay;
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @BeforeEach
    void setUp(){
        reservation = Reservation.builder().customerId(12345L)
                .expectedCheckinTime(LocalDateTime.parse("12-12-2020 11:59",format))
                .expectedCheckoutTime(LocalDateTime.parse("01-01-2021 11:00",format))
                .hourlyRate(23000)
                .reservationId(1l)
                .roomType("deluxe")
                .status("paid")
                .build();
        overdueStay = CustomerOverdueStay.builder()
                .checkOutTime(LocalDateTime.parse("01-01-2021 11:00",format))
                .customerId(12345L)
                .expectedCheckoutTime(LocalDateTime.parse("01-01-2021 11:00",format))
                .overStayAmount(29000)
                .overstayHours(4)
                .roomType("deluxe")
                .build();
    }

    @Test
    void getOverstay() throws Exception {
        Mockito.when(reservationService.getCustomerOverStay(12345L)).thenReturn(overdueStay);
        webMvcTest.perform(MockMvcRequestBuilders.get("/api/v1/overstay/12345").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void getReservations() throws Exception {
        Mockito.when(reservationService.getReservations()).thenReturn(Arrays.asList(reservation));
        webMvcTest.perform(MockMvcRequestBuilders.get("/api/v1/reservations").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
