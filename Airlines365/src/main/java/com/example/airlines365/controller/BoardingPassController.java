package com.example.airlines365.controller;

import com.example.airlines365.dto.BoardingPassRequest;
import com.example.airlines365.dto.BoardingPassResponse;
import com.example.airlines365.exception.AssentoOcupadoException;
import com.example.airlines365.exception.FileiraEmergenciaException;
import com.example.airlines365.exception.MalaNaoDespachadaException;
import com.example.airlines365.exception.PassageiroNaoEncontradoException;
import com.example.airlines365.model.BoardingPass;
import com.example.airlines365.service.BoardingPassService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/passageiros/confirmacao")
@AllArgsConstructor
public class BoardingPassController {

    private BoardingPassService service;
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<BoardingPassResponse> checkIn(@RequestBody @Valid BoardingPassRequest request)
            throws PassageiroNaoEncontradoException, AssentoOcupadoException,
            FileiraEmergenciaException, MalaNaoDespachadaException {

        Long cpf = request.getCpf();
        BoardingPass confirmacao = mapper.map(request, BoardingPass.class);
        confirmacao = service.checkin(cpf, confirmacao);
        BoardingPassResponse response = mapper.map(confirmacao, BoardingPassResponse.class);
        return ResponseEntity.created(URI.create(response.getEticket())).body(response);
    }
}
