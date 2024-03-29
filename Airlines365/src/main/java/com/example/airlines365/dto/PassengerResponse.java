package com.example.airlines365.dto;

import com.example.airlines365.model.enums.Classification;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PassengerResponse {

    private Long cpf;
    private String nome;
    private LocalDate dataNascimento;
    private Classification classificacao;
    private Integer milhas;
}
