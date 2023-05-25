package com.example.airlines365.service;

import com.example.airlines365.exception.PassageiroNaoEncontradoException;
import com.example.airlines365.model.Passenger;
import com.example.airlines365.repository.PassengerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PassengerService {

    private PassengerRepository repository;

    public List<Passenger> consultar(){
        return repository.findAll();
    }

    public Passenger consultar(Long cpf) throws PassageiroNaoEncontradoException {
        return repository.findById(cpf).orElseThrow(PassageiroNaoEncontradoException::new);
    }
}
