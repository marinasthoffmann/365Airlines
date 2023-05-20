package com.example.airlines365.model;

import com.example.airlines365.model.enums.Classification;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Passenger {

    @Id
    private Long cpf;
    private String nome;
    private LocalDate birthdate;
    private Classification classification;
    private Integer mileage;
    private String eticket;
    private String seat;
    private Boolean checkedBag;
    private LocalDateTime confirmationTime;
}
