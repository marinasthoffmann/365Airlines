package com.example.airlines365.service;

import com.example.airlines365.model.enums.Seat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatService {

    public List<String> consultar() {

        List<String> assentos = new ArrayList<>();

        for (Seat seat : Seat.values()) {
            assentos.add(seat.getValue());
        }
        return assentos;
    }
}
