package com.carbon.assessment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data //lombok for getter and setters
@AllArgsConstructor
@NoArgsConstructor
public class OverdueRates {
    @Id
    private long id;
    private String roomType;
    private float weekDayRate;
    private float weekEndRate;
}
