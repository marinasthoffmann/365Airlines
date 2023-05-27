package com.example.airlines365.service;

import com.example.airlines365.exception.*;
import com.example.airlines365.model.BoardingPass;
import com.example.airlines365.model.Passenger;
import com.example.airlines365.model.enums.Classification;
import com.example.airlines365.model.enums.Seat;
import com.example.airlines365.repository.BoardingPassRepository;
import com.example.airlines365.repository.PassengerRepository;
import com.example.airlines365.utils.StringToSeatConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.Mapping;
import org.modelmapper.spi.MappingContext;
import org.modelmapper.spi.MappingEngine;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BoardingPassServiceTest {

    @Mock
    private PassengerRepository passengerRepository;
    @Mock
    private BoardingPassRepository boardingPassRepository;

    @InjectMocks
    private BoardingPassService service;

    @Test
    @DisplayName("Quando tenta fazer checkin do passageiro deve retornar passageiro e confirmação preenchidas")
    void checkin_clienteVIP() throws AssentoOcupadoException, MalaNaoDespachadaException,
            FileiraEmergenciaException, PassageiroNaoEncontradoException {
        Long cpf = 11111111111L;
        BoardingPass confirmacao = new BoardingPass();
        confirmacao.setAssento(Seat.A1);
        confirmacao.setMalasDespachadas(true);
        Passenger passageiro = new Passenger(
                cpf, "James Tiberius Kirk", LocalDate.of(1933, 10, 01),
                Classification.VIP, 100, null);

        Mockito.when(passengerRepository.findById(cpf)).thenReturn(Optional.of(passageiro));
        Mockito.when (boardingPassRepository.save(confirmacao)).thenReturn(confirmacao);

        BoardingPass result = service.checkin(cpf, confirmacao);
        assertNotNull(result);
        assertNotNull(result.getEticket());
        assertNotNull(result.getDataHoraConfirmacao());
        assertEquals(passageiro.getMilhas(), 200);
        assertEquals(confirmacao, passageiro.getConfirmacao());
    }

    @Test
    @DisplayName("Quando tenta fazer checkin do passageiro deve retornar passageiro e confirmação preenchidas")
    void checkin_clienteOuro() throws AssentoOcupadoException, MalaNaoDespachadaException,
            FileiraEmergenciaException, PassageiroNaoEncontradoException {
        Long cpf = 11111111111L;
        BoardingPass confirmacao = new BoardingPass();
        confirmacao.setAssento(Seat.A1);
        confirmacao.setMalasDespachadas(true);
        Passenger passageiro = new Passenger(
                cpf, "James Tiberius Kirk", LocalDate.of(1933, 10, 01),
                Classification.OURO, 100, null);

        Mockito.when(passengerRepository.findById(cpf)).thenReturn(Optional.of(passageiro));
        Mockito.when (boardingPassRepository.save(confirmacao)).thenReturn(confirmacao);

        BoardingPass result = service.checkin(cpf, confirmacao);
        assertNotNull(result);
        assertNotNull(result.getEticket());
        assertNotNull(result.getDataHoraConfirmacao());
        assertEquals(passageiro.getMilhas(), 180);
        assertEquals(confirmacao, passageiro.getConfirmacao());
    }

    @Test
    @DisplayName("Quando tenta fazer checkin em assento já ocupado deve retornar exceção")
    void checkin_assentoOcupado() throws AssentoOcupadoException, MalaNaoDespachadaException,
            FileiraEmergenciaException, PassageiroNaoEncontradoException {
        Long cpf = 11111111111L;
        BoardingPass confirmacao = new BoardingPass();
        confirmacao.setAssento(Seat.A1);
        confirmacao.setMalasDespachadas(true);
        Passenger passageiro = new Passenger(
                cpf, "James Tiberius Kirk", LocalDate.of(1933, 10, 01),
                Classification.OURO, 100, null);

        Mockito.when(passengerRepository.findById(cpf)).thenReturn(Optional.of(passageiro));
        Mockito.when(boardingPassRepository.existsByAssento(Seat.A1)).thenReturn(true);

        assertThrows(AssentoOcupadoException.class, () -> service.checkin(cpf, confirmacao));
    }

    @Test
    @DisplayName("Quando tenta fazer checkin de passageiro com CPF não registrado deve retornar exceção")
    void checkin_cpfInexistente() {
        Mockito.when(passengerRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        BoardingPass confirmacao = new BoardingPass();
        confirmacao.setAssento(Seat.A1);
        confirmacao.setMalasDespachadas(true);
        assertThrows(PassageiroNaoEncontradoException.class, () -> service.checkin(11111111111L, confirmacao));
    }

    @Test
    @DisplayName("Quando tenta fazer checkin de passageiro com assento não registrado deve retornar exceção")
    void checkin_assentoInexistente()  {
        assertThrows(IllegalArgumentException.class, () -> Seat.valueOf("0A"));
    }

    @Test
    @DisplayName("Quando passageiro menor de idade tenta fazer checkin em fileira de emergencia deve retornar exceção")
    void checkin_menorDeIdadeEmFileiraEmergencia() throws AssentoOcupadoException, MalaNaoDespachadaException,
            FileiraEmergenciaException, PassageiroNaoEncontradoException {
        Long cpf = 11111111111L;
        BoardingPass confirmacao = new BoardingPass();
        confirmacao.setAssento(Seat.A4);
        confirmacao.setMalasDespachadas(true);
        Passenger passageiro = new Passenger(
                cpf, "James Tiberius Kirk", LocalDate.of(2020, 10, 01),
                Classification.VIP, 100, null);

        Mockito.when(passengerRepository.findById(cpf)).thenReturn(Optional.of(passageiro));

        assertThrows(FileiraEmergenciaException.class, () -> service.checkin(cpf, confirmacao));
    }

    @Test
    @DisplayName("Quando passageiro tenta fazer checkin sem despachar mala em fileira de emergencia deve retornar exceção")
    void checkin_malaNaoDespachadaEmFileiraEmergencia() throws AssentoOcupadoException, MalaNaoDespachadaException,
            FileiraEmergenciaException, PassageiroNaoEncontradoException {
        Long cpf = 11111111111L;
        BoardingPass confirmacao = new BoardingPass();
        confirmacao.setAssento(Seat.A4);
        confirmacao.setMalasDespachadas(false);
        Passenger passageiro = new Passenger(
                cpf, "James Tiberius Kirk", LocalDate.of(1933, 10, 01),
                Classification.VIP, 100, null);

        Mockito.when(passengerRepository.findById(cpf)).thenReturn(Optional.of(passageiro));

        assertThrows(MalaNaoDespachadaException.class, () -> service.checkin(cpf, confirmacao));
    }
}