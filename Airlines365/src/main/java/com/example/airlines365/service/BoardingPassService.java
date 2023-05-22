package com.example.airlines365.service;

import com.example.airlines365.exception.RegistroNaoEncontradoException;
import com.example.airlines365.model.BoardingPass;
import com.example.airlines365.model.Passenger;
import com.example.airlines365.model.enums.Seat;
import com.example.airlines365.repository.BoardingPassRepository;
import com.example.airlines365.repository.PassengerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BoardingPassService {


    private PassengerRepository passengerRepository;
    private BoardingPassRepository boardingPassRepository;

    public BoardingPass checkin(Long cpf, BoardingPass confirmacao) throws RegistroNaoEncontradoException {
        Passenger passageiro = passengerRepository.findById(cpf).orElseThrow(RegistroNaoEncontradoException::new);
        confirmacao.setPassageiro(passageiro);
        confirmacao.setEticket(generateEticket());
        confirmacao.setDataHoraConfirmacao(LocalDateTime.now());
        confirmacao = boardingPassRepository.save(confirmacao);
        return confirmacao;
    }

    private String generateEticket() {
        return UUID.randomUUID().toString();
    }
}
