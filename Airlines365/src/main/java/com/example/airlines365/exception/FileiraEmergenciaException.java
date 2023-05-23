package com.example.airlines365.exception;

public class FileiraEmergenciaException extends Throwable{
    public FileiraEmergenciaException(){
        super("O assento escolhido fica em uma fileira de emergência. O passageiro deve ser maior de idade para ocupa-lo.");
    }
}
