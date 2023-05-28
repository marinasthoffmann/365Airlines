package com.example.airlines365.controller;

import com.example.airlines365.dto.BoardingPassRequest;
import com.example.airlines365.dto.BoardingPassResponse;
import com.example.airlines365.exception.AssentoOcupadoException;
import com.example.airlines365.exception.FileiraEmergenciaException;
import com.example.airlines365.exception.MalaNaoDespachadaException;
import com.example.airlines365.exception.PassageiroNaoEncontradoException;
import com.example.airlines365.model.BoardingPass;
import com.example.airlines365.model.enums.Seat;
import com.example.airlines365.service.BoardingPassService;
import com.example.airlines365.service.PassengerService;
import com.example.airlines365.service.SeatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class BoardingPassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BoardingPassService boardingPassService;

    @MockBean
    private PassengerService passengerService;

    @MockBean
    private SeatService seatService;

    @Test
    @DisplayName("Quando check-in de passageiro, deve retornar sucesso")
    void checkIn() throws Exception, AssentoOcupadoException, MalaNaoDespachadaException,
            FileiraEmergenciaException, PassageiroNaoEncontradoException {
        Long cpf = 11111111111L;
        BoardingPassRequest request = new BoardingPassRequest(cpf, "1A", true);
        BoardingPass confirmacao = new BoardingPass("1234", Seat.A1, true, LocalDateTime.now());

        BoardingPassResponse response = modelMapper.map(confirmacao, BoardingPassResponse.class);
        Mockito.when(boardingPassService.checkin(Mockito.anyLong(), Mockito.any(BoardingPass.class))).thenReturn(confirmacao);

        mockMvc.perform(post("/api/passageiros/confirmacao")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.eticket", is(notNullValue())))
                .andExpect(jsonPath("$.eticket", is(response.getEticket())));
    }
}