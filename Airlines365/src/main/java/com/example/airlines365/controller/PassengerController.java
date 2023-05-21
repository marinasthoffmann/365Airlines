package com.example.airlines365.controller;

import com.example.airlines365.dto.PassengerResponse;
import com.example.airlines365.model.Passenger;
import com.example.airlines365.service.PassengerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/passageiros")
@AllArgsConstructor
public class PassengerController {

    private PassengerService service;
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<PassengerResponse>> consultar() {
        List<Passenger> passageiros = service.consultar();
        List<PassengerResponse> response = passageiros.stream()
                .map(p -> mapper.map(p, PassengerResponse.class))
                .toList();

        return ResponseEntity.ok(response);
    }
}
