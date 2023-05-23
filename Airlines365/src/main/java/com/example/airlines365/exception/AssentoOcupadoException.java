package com.example.airlines365.exception;

public class AssentoOcupadoException extends Throwable{
    public AssentoOcupadoException(){
        super("Assento ocupado por outro passageiro");
    }
}