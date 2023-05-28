package com.example.airlines365.controller;

import com.example.airlines365.exception.PassageiroNaoEncontradoException;
import com.example.airlines365.model.BoardingPass;
import com.example.airlines365.model.Passenger;
import com.example.airlines365.model.enums.Classification;
import com.example.airlines365.model.enums.Seat;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class PassengerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PassengerService passengerService;

    @MockBean
    private BoardingPassService boardingPassService;

    @MockBean
    private SeatService seatService;

    @Test
    @DisplayName("Quando há passageiros registrados, deve retornar lista com passageiros")
    void consultar() throws Exception {
        var confirmacao = new BoardingPass("123", Seat.A1, true, LocalDateTime.now());
        var passageiros = List.of(
                new Passenger(11111111111L, "João", LocalDate.of(1990, 5, 10),
                        Classification.VIP, 100, null),
                new Passenger(22222222222L, "Maria", LocalDate.of(1992, 9, 15),
                        Classification.OURO, 100, confirmacao)
        );

        Mockito.when(passengerService.consultar()).thenReturn(passageiros);
        mockMvc.perform(get("/api/passageiros")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // 200
                .andExpect(jsonPath("$[0].cpf", is(passageiros.get(0).getCpf())))
                .andExpect(jsonPath("$[0].nome", is(passageiros.get(0).getNome())))
                .andExpect(jsonPath("$[1].nome", is(passageiros.get(1).getNome())));
    }

    @Test
    @DisplayName("Quando consultar por cpf, deve retornar passageiro correspondente")
    void consultar_porCpf() throws Exception, PassageiroNaoEncontradoException {
        Long cpf = 11111111111L;
        var passageiro = new Passenger(cpf, "João", LocalDate.of(1990, 5, 10),
                        Classification.VIP, 100, null);
        Mockito.when(passengerService.consultar(Mockito.anyLong())).thenReturn(passageiro);
        mockMvc.perform(get("/api/passageiros/{cpf}", cpf)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // 200
                .andExpect(jsonPath("$.cpf", is(passageiro.getCpf())));
    }

    @Test
    @DisplayName("Quando consultar por cpf não registrado, deve retornar exceção")
    void consultar_porCpfInexistente() throws Exception, PassageiroNaoEncontradoException {
        Mockito.when(passengerService.consultar(Mockito.anyLong())).thenThrow(PassageiroNaoEncontradoException.class);
        mockMvc.perform(get("/api/passageiros/{cpf}", 11111111111L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());  // 404
    }
}