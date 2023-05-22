package com.example.airlines365.utils;

import com.example.airlines365.model.enums.Seat;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class StringToSeatConverter implements Converter<String, Seat> {

    @Override
    public Seat convert(MappingContext<String, Seat> mappingContext) {
        String source = mappingContext.getSource();
        if (source == null) {
            return null;
        }
        StringBuilder assento_reverso = new StringBuilder(source);
        return Seat.valueOf(assento_reverso.reverse().toString());
    }
}
