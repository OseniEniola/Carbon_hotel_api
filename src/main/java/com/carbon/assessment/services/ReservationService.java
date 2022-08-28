package com.carbon.assessment.services;

import com.carbon.assessment.entity.Reservation;
import com.carbon.assessment.error.GlobalException;
import com.carbon.assessment.model.CustomerOverdueStay;

import java.util.List;

public interface ReservationService {
    List<Reservation> getReservations();
    CustomerOverdueStay getCustomerOverStay(Long id) throws GlobalException;
    Reservation createReservation(Reservation reservation);
}
