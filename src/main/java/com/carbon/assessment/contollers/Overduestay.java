package com.carbon.assessment.contollers;


import com.carbon.assessment.entity.Reservation;
import com.carbon.assessment.error.GlobalException;
import com.carbon.assessment.model.CustomerOverdueStay;
import com.carbon.assessment.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class Overduestay {
    private final ReservationService reservationService;
    // Get Reservations
    @GetMapping(value = "/reservations",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getReservations(){
    List<Reservation> reservations = reservationService.getReservations();
     return ResponseEntity.status(HttpStatus.OK).body(reservations);
    }

    //Get Customer overstay fee
    @GetMapping(value="/overstay/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getOverstayfee(@PathVariable("customerId") Long id) throws GlobalException {
        CustomerOverdueStay overdueStay = reservationService.getCustomerOverStay(id);
        return ResponseEntity.status(HttpStatus.OK).body(overdueStay);
    }

    //Create new Reservation
    @PostMapping(value = "/reservation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createReservation(@Valid @RequestBody Reservation reservation){
        reservationService.createReservation(reservation);
        return  ResponseEntity.status(HttpStatus.CREATED).body("Created Successfully");
    }
}
