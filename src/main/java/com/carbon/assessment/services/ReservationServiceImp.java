package com.carbon.assessment.services;

import com.carbon.assessment.entity.OverdueRates;
import com.carbon.assessment.entity.Reservation;
import com.carbon.assessment.error.GlobalException;
import com.carbon.assessment.model.CustomerOverdueStay;
import com.carbon.assessment.repository.RateRepo;
import com.carbon.assessment.repository.ReservationRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationServiceImp implements ReservationService{

    private final ReservationRepo reservationRepo;
    private final RateRepo rateRepo;
    @Override
    public List<Reservation> getReservations() {
        return reservationRepo.findAll();
    }

    @Override
    public CustomerOverdueStay getCustomerOverStay(Long id) throws GlobalException {
        Optional<Reservation> reservation = reservationRepo.findByCustomerId(id);
        //Check if the reservatin exists
        if(reservation.isEmpty()){
            throw new GlobalException("Reservation not found");
        }
        //Check if the room type is available
        Optional<OverdueRates> roomRate = rateRepo.findByRoomType(reservation.get().getRoomType().toLowerCase());
        if(roomRate.isEmpty()){
            throw new GlobalException("Roomtype not found");
        }

        LocalDateTime currentDate= LocalDateTime.now().withNano(0);
        DayOfWeek isWeekday = currentDate.getDayOfWeek();
        float overstayAmount= 0.0F;
        int hrs=0;

        //if the currendate(assumed to be the checkout time) time is after the expected checkout time,
        // user has overstayed, calculate overdue stay
        if(currentDate.isAfter(reservation.get().getExpectedCheckoutTime())){
            Duration diff = Duration.between(reservation.get().getExpectedCheckoutTime(),currentDate);
            long minutes = diff.toMinutes();
            //over stay hours
            hrs =(int) Math.ceil(((float)minutes)/60);
            if(isWeekday.getValue() > 5){
                overstayAmount = (roomRate.get().getWeekEndRate()/100) * hrs * reservation.get().getHourlyRate();
            }else{
                overstayAmount =  (roomRate.get().getWeekEndRate()/100) * hrs * reservation.get().getHourlyRate();
            }
        }
        return new CustomerOverdueStay(
                id,
                reservation.get().getRoomType(),
                overstayAmount,
                reservation.get().getExpectedCheckoutTime(),
                currentDate,
                hrs);
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        return reservationRepo.save(reservation);
    }
}
