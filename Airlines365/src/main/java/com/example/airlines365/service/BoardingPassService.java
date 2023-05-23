package com.example.airlines365.service;

import com.example.airlines365.exception.PassageiroNaoEncontradoException;
import com.example.airlines365.model.BoardingPass;
import com.example.airlines365.model.Passenger;
import com.example.airlines365.repository.BoardingPassRepository;
import com.example.airlines365.repository.PassengerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BoardingPassService {


    private PassengerRepository passengerRepository;
    private BoardingPassRepository boardingPassRepository;

    public BoardingPass checkin(Long cpf, BoardingPass confirmacao) throws PassageiroNaoEncontradoException {
        Passenger passageiro = passengerRepository.findById(cpf).orElseThrow(PassageiroNaoEncontradoException::new);
        String eticket = generateEticket();
        confirmacao.setEticket(eticket);
        confirmacao.setDataHoraConfirmacao(LocalDateTime.now());
        confirmacao = boardingPassRepository.save(confirmacao);
        passageiro.setConfirmacao(confirmacao);
        passengerRepository.save(passageiro);
        return confirmacao;
    }

    private String generateEticket() {
        return UUID.randomUUID().toString();
    }
}
