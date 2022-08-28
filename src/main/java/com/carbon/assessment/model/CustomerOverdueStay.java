package com.carbon.assessment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerOverdueStay {
    private Long customerId;
    private String roomType;
    private double overStayAmount;
    private LocalDateTime expectedCheckoutTime;
    private LocalDateTime checkOutTime;
    private int overstayHours;

}
