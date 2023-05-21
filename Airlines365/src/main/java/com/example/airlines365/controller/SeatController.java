package com.example.airlines365.controller;

import com.example.airlines365.service.SeatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/assentos")
@AllArgsConstructor
public class SeatController {

    private SeatService service;

    @GetMapping
    public ResponseEntity<List<String>> consultar() {
        List<String> assentos = service.consultar();
        return ResponseEntity.ok(assentos);
    }

}
