package com.example.airlines365.controller;

import com.example.airlines365.service.BoardingPassService;
import com.example.airlines365.service.PassengerService;
import com.example.airlines365.service.SeatService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class SeatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeatService seatService;

    @MockBean
    private BoardingPassService boardingPassService;

    @MockBean
    private PassengerService passengerService;

    @Test
    @DisplayName("Quando consultar assentos, deve retornar lista de assentos")
    void consultar() throws Exception {

        List<String> assentosEsperados = Arrays.asList(
                "1A", "1B", "1C", "1D", "1E", "1F",
                "2A", "2B", "2C", "2D", "2E", "2F",
                "3A", "3B", "3C", "3D", "3E", "3F",
                "4A", "4B", "4C", "4D", "4E", "4F",
                "5A", "5B", "5C", "5D", "5E", "5F",
                "6A", "6B", "6C", "6D", "6E", "6F",
                "7A", "7B", "7C", "7D", "7E", "7F",
                "8A", "8B", "8C", "8D", "8E", "8F",
                "9A", "9B", "9C", "9D", "9E", "9F"
        );

        Mockito.when(seatService.consultar()).thenReturn(assentosEsperados);

        mockMvc.perform(get("/api/assentos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(assentosEsperados.size()))
                .andExpect(jsonPath("$[0]").value(assentosEsperados.get(0)));
    }
}