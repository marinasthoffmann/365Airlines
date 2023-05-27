package com.example.airlines365.service;

import com.example.airlines365.exception.PassageiroNaoEncontradoException;
import com.example.airlines365.model.Passenger;
import com.example.airlines365.model.enums.Classification;
import com.example.airlines365.repository.PassengerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PassengerServiceTest {

    @Mock
    private PassengerRepository passengerRepository;

    @InjectMocks
    private PassengerService service;

    @Test
    @DisplayName("Ao consultar todos passageiros deve retornar lista preenchida")
    void consultar_todosPassageiros() {
        Passenger passageiro1 = new Passenger(11111111111L, "João", LocalDate.of(1990, 5, 10),
                Classification.VIP, 100, null);
        Passenger passageiro2 = new Passenger(22222222222L, "Maria", LocalDate.of(1992, 9, 15),
                Classification.OURO, 100, null);
        List<Passenger> passageiros = Arrays.asList(passageiro1, passageiro2);

        Mockito.when(passengerRepository.findAll()).thenReturn(passageiros);

        List<Passenger> resultado = service.consultar();

        assertEquals(resultado.size(), 2);
        assertEquals(resultado.get(0), passageiro1);
    }

    @Test
    @DisplayName("Ao consultar passageiro por cpf deve retornar o passageiro correspondente")
    void consultar_porCpf() throws PassageiroNaoEncontradoException {
        Long cpf = 11111111111L;
        Passenger passageiro = new Passenger(cpf, "João", LocalDate.of(1990, 5, 10),
                Classification.VIP, 100, null);

        Mockito.when(passengerRepository.findById(cpf)).thenReturn(Optional.of(passageiro));

        Passenger resultado = service.consultar(cpf);

        Assertions.assertEquals(passageiro, resultado);
    }

    @Test
    @DisplayName("Ao consultar passageiro por cpf não registrado deve retornar exceção")
    void consultar_porCpfInexistente() throws PassageiroNaoEncontradoException {
        Long cpfInexistente = 987654321L;

        Mockito.when(passengerRepository.findById(cpfInexistente)).thenReturn(Optional.empty());

        Assertions.assertThrows(PassageiroNaoEncontradoException.class, () -> service.consultar(cpfInexistente));
    }
}