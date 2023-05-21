package com.example.airlines365.service;

import com.example.airlines365.model.enums.Seat;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatService {

    public List<String> consultar() {
        return Arrays.stream(Seat.values())
                .map(SeatService::getFormattedSeat)
                .collect(Collectors.toList());
    }

    public static String getFormattedSeat(Seat value) {
        StringBuilder assento_reverso = new StringBuilder(value.toString());
        return assento_reverso.reverse().toString();
    }
}
