package com.example.airlines365.exception;

public class PassageiroNaoEncontradoException extends Throwable{

    public PassageiroNaoEncontradoException(){
        super("Passageiro não encontrado");
    }
}
