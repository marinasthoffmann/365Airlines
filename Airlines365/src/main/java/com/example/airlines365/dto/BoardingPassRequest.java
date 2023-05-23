package com.example.airlines365.dto;

import com.example.airlines365.model.enums.Seat;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class BoardingPassRequest {

    @NotNull(message = "{campo.obrigatorio}")
    @Digits(integer = 11, fraction = 0)
    private Long cpf;

    @NotEmpty(message = "{campo.obrigatorio}")
    @Pattern(regexp = "^[0-9][a-zA-Z]$")
    private String assento;

    @NotNull(message = "{campo.obrigatorio}")
    private Boolean malasDespachadas;
}
