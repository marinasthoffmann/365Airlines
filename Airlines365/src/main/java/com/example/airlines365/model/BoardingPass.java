package com.example.airlines365.model;

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

    @OneToOne
    @JoinColumn(name = "cpf", referencedColumnName = "cpf")
    private Passenger passageiro;
    @Id
    private String eticket;
    private String assento;
    private Boolean malasDespachadas;
    private LocalDateTime dataHoraConfirmacao;
}
