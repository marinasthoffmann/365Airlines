package com.example.airlines365.utils;

import com.example.airlines365.exception.AssentoNaoExistenteException;
import com.example.airlines365.model.enums.Seat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.Mapping;
import org.modelmapper.spi.MappingContext;
import org.modelmapper.spi.MappingEngine;

import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringToSeatConverterTest {

    private final StringToSeatConverter converter = new StringToSeatConverter();

    @Test
    @DisplayName("Deve converter uma string para um assento existente")
    void converterAssentoExistente() {
        // String de entrada
        String input = "1A";

        // Contexto de mapeamento simulado
        MappingContext<String, Seat> mappingContext = new MappingContext<String, Seat>() {
            @Override
            public String getSource() {
                return input;
            }

            @Override
            public Class<String> getSourceType() {
                return null;
            }

            @Override
            public TypeMap<String, Seat> getTypeMap() {
                return null;
            }

            @Override
            public String getTypeMapName() {
                return null;
            }

            @Override
            public <CS, CD> MappingContext<CS, CD> create(CS cs, CD cd) {
                return null;
            }

            @Override
            public <CS, CD> MappingContext<CS, CD> create(CS cs, Class<CD> aClass) {
                return null;
            }

            @Override
            public <CS, CD> MappingContext<CS, CD> create(CS cs, Type type) {
                return null;
            }

            @Override
            public Seat getDestination() {
                return null;
            }

            @Override
            public Class<Seat> getDestinationType() {
                return null;
            }

            @Override
            public Type getGenericDestinationType() {
                return null;
            }

            @Override
            public Mapping getMapping() {
                return null;
            }

            @Override
            public MappingEngine getMappingEngine() {
                return null;
            }

            @Override
            public MappingContext<?, ?> getParent() {
                return null;
            }
        };

        // Converter a string para enum
        Seat seat = converter.convert(mappingContext);

        // Verificar se o assento foi convertido corretamente
        assertEquals(Seat.A1, seat);
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao converter uma string para um assento inexistente")
    void converterAssentoInexistente() {
        // String de entrada com um assento inexistente
        String input = "10Z";

        // Contexto de mapeamento simulado
        MappingContext<String, Seat> mappingContext = new MappingContext<String, Seat>() {
            @Override
            public String getSource() {
                return input;
            }

            @Override
            public Class<String> getSourceType() {
                return null;
            }

            @Override
            public TypeMap<String, Seat> getTypeMap() {
                return null;
            }

            @Override
            public String getTypeMapName() {
                return null;
            }

            @Override
            public <CS, CD> MappingContext<CS, CD> create(CS cs, CD cd) {
                return null;
            }

            @Override
            public <CS, CD> MappingContext<CS, CD> create(CS cs, Class<CD> aClass) {
                return null;
            }

            @Override
            public <CS, CD> MappingContext<CS, CD> create(CS cs, Type type) {
                return null;
            }

            @Override
            public Seat getDestination() {
                return null;
            }

            @Override
            public Class<Seat> getDestinationType() {
                return null;
            }

            @Override
            public Type getGenericDestinationType() {
                return null;
            }

            @Override
            public Mapping getMapping() {
                return null;
            }

            @Override
            public MappingEngine getMappingEngine() {
                return null;
            }

            @Override
            public MappingContext<?, ?> getParent() {
                return null;
            }
        };

        // Verificar se a exceção AssentoNaoExistenteException é lançada ao converter a string
        Assertions.assertThrows(AssentoNaoExistenteException.class, () -> converter.convert(mappingContext));
    }
}
