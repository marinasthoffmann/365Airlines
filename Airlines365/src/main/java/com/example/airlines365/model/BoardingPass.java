package com.example.airlines365.model;

import com.example.airlines365.model.enums.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EMBARQUES")
public class BoardingPass {

    @Id
    private String eticket;
    @Enumerated(EnumType.STRING)
    private Seat assento;
    private Boolean malasDespachadas;
    private LocalDateTime dataHoraConfirmacao;
}
