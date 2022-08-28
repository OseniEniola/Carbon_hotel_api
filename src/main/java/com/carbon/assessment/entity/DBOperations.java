package com.carbon.assessment.entity;

import com.carbon.assessment.repository.RateRepo;
import com.carbon.assessment.repository.ReservationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DBOperations implements CommandLineRunner {

    private final ReservationRepo reserationRepo;
    private final RateRepo overdueRates;

    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @Override
    public void run(String... args) throws Exception {
        reserationRepo.saveAll(Arrays.asList(
                new Reservation(12000L,"deluxe",12000L,230000,"paid",LocalDateTime.parse("12-12-2020 11:59",format),LocalDateTime.parse("01-01-2021 11:00",format)),
                new Reservation(12001L,"regular",12324L,150000,"paid",LocalDateTime.parse("12-12-2020 12:00",format),LocalDateTime.parse("27-08-2022 16:07",format)),
                new Reservation(12002L,"palatial",12100L,560000,"paid",LocalDateTime.parse("12-12-2020 12:00",format),LocalDateTime.parse("01-01-2021 11:00",format)),
                new Reservation(12003L,"regular",12323L,200000,"paid",LocalDateTime.parse("25-12-2020 12:00",format),LocalDateTime.parse("04-01-2021 11:00",format))
        ));

        overdueRates.saveAll(Arrays.asList(
                new OverdueRates(1,"regular",7,10),
                new OverdueRates(2,"deluxe",8.5F,12),
                new OverdueRates(3,"palatial",11,16)
        ));
    }

}
