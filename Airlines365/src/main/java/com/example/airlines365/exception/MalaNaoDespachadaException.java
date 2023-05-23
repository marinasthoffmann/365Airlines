package com.example.airlines365.exception;

public class MalaNaoDespachadaException extends Throwable{
    public MalaNaoDespachadaException(){
        super("O assento escolhido fica em uma fileira de emergência. O passageiro deve despachar a mala.");
    }
}
