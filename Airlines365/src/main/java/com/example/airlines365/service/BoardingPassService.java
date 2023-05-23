package com.example.airlines365.service;

import com.example.airlines365.exception.AssentoOcupadoException;
import com.example.airlines365.exception.FileiraEmergenciaException;
import com.example.airlines365.exception.PassageiroNaoEncontradoException;
import com.example.airlines365.model.BoardingPass;
import com.example.airlines365.model.Passenger;
import com.example.airlines365.repository.BoardingPassRepository;
import com.example.airlines365.repository.PassengerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class BoardingPassService {


    private PassengerRepository passengerRepository;
    private BoardingPassRepository boardingPassRepository;

    public BoardingPass checkin(Long cpf, BoardingPass confirmacao)
            throws PassageiroNaoEncontradoException, AssentoOcupadoException, FileiraEmergenciaException {
        Passenger passageiro = passengerRepository.findById(cpf).orElseThrow(PassageiroNaoEncontradoException::new);
        verificaRegrasDeNegocio(passageiro, confirmacao);
        String eticket = generateEticket();
        confirmacao.setEticket(eticket);
        confirmacao.setDataHoraConfirmacao(LocalDateTime.now());
        confirmacao = boardingPassRepository.save(confirmacao);
        passageiro.setConfirmacao(confirmacao);
        passengerRepository.save(passageiro);
        return confirmacao;
    }

    private void verificaRegrasDeNegocio(Passenger passageiro, BoardingPass confirmacao)
            throws AssentoOcupadoException, FileiraEmergenciaException {
        verificaDisponibilidadeAssento(confirmacao);
        verificaFileiraEmergencia(passageiro, confirmacao);
    }

    private void verificaDisponibilidadeAssento(BoardingPass confirmacao) throws AssentoOcupadoException {
        boolean assentoOcupado = boardingPassRepository.existsByAssento(confirmacao.getAssento());
        if (assentoOcupado)
            throw new AssentoOcupadoException();
    }

    private void verificaFileiraEmergencia(Passenger passageiro, BoardingPass confirmacao)
            throws FileiraEmergenciaException {
        Pattern pattern = Pattern.compile("[4-5]");
        Boolean ehFileiraEmergencia = pattern.matcher(confirmacao.getAssento().toString()).find();
        Boolean passageiroEhMenorIdade =
                (Period.between(passageiro.getDataNascimento(), LocalDate.now()).getYears()) < 18;
        if (ehFileiraEmergencia && passageiroEhMenorIdade) {
            throw new FileiraEmergenciaException();
        }
    }

    private String generateEticket() {
        return UUID.randomUUID().toString();
    }
}
