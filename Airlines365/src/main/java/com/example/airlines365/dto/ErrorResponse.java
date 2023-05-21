package com.example.airlines365.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ErrorResponse {

    private String mensagem;

    public ErrorResponse(String mensagem){
        this.mensagem = mensagem;
    }
}
