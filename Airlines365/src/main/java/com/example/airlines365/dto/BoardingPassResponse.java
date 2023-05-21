package com.example.airlines365.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardingPassResponse {
    private String eticket;
    private LocalDateTime dataHoraConfirmacao = LocalDateTime.now();

}
