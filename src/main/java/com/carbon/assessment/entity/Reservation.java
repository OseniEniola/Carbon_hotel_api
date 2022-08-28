package com.carbon.assessment.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reservationId;
    @NotBlank(message="Room type is required")
    private String roomType;
    @Min(value=0, message="Customer id is required")
    private Long customerId;
    @Min(value=0, message="Hourly rate is required")
    private float hourlyRate;
    @NotBlank(message="Status is required")
    private String status;
    private LocalDateTime expectedCheckinTime;
    @Column()
    private LocalDateTime expectedCheckoutTime;
}
