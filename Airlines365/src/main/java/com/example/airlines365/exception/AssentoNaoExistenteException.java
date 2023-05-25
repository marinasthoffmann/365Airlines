package com.example.airlines365.exception;

public class AssentoNaoExistenteException extends RuntimeException{
    public AssentoNaoExistenteException(){
        super("Assento não existente");
    }
}
