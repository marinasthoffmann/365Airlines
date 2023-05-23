package com.example.airlines365.model;

import com.example.airlines365.model.enums.Classification;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PASSAGEIROS")
public class Passenger {

    @Id
    private Long cpf;
    private String nome;
    private LocalDate dataNascimento;
    @Enumerated(EnumType.STRING)
    private Classification classificacao;
    private Integer milhas;
    @OneToOne
    @JoinColumn(name = "eticket", referencedColumnName = "eticket")
    private BoardingPass confirmacao;

    public void adicionarMilhas(int milhas) {
        this.milhas += milhas;
    }
}
