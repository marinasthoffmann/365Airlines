package com.example.airlines365.controller;

import com.example.airlines365.dto.PassengerConfirmationResponse;
import com.example.airlines365.dto.PassengerResponse;
import com.example.airlines365.exception.RegistroNaoEncontradoException;
import com.example.airlines365.model.Passenger;
import com.example.airlines365.service.PassengerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/passageiros")
@AllArgsConstructor
public class PassengerController {

    private PassengerService service;
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<PassengerConfirmationResponse>> consultar() {
        List<Passenger> passageiros = service.consultar();
        List<PassengerConfirmationResponse> response = passageiros.stream()
                .map(p -> {
                    PassengerConfirmationResponse passengerResponse = mapper.map(p, PassengerConfirmationResponse.class);
                    if (p.getConfirmacao() != null && p.getConfirmacao().getAssento() != null) {
                        passengerResponse.setAssento(p.getConfirmacao().getAssento());
                    }
                    return passengerResponse;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("{cpf}")
    public ResponseEntity<PassengerResponse> consultar(@PathVariable("cpf") Long cpf)
            throws RegistroNaoEncontradoException {
        Passenger passageiro = service.consultar(cpf);
        PassengerResponse response = mapper.map(passageiro, PassengerResponse.class);

        return ResponseEntity.ok(response);
    }
}
