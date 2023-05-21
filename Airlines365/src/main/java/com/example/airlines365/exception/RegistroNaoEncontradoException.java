package com.example.airlines365.exception;

public class RegistroNaoEncontradoException extends Throwable{

    public RegistroNaoEncontradoException(){
        super("Passageiro não encontrado");
    }
}
