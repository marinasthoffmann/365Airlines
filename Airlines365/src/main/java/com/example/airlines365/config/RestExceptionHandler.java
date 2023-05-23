package com.example.airlines365.config;

import com.example.airlines365.dto.ErrorResponse;
import com.example.airlines365.exception.AssentoNaoExistenteException;
import com.example.airlines365.exception.PassageiroNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PassageiroNaoEncontradoException.class)
    public ResponseEntity<Object> handlePassageiroNaoEncontradoException(PassageiroNaoEncontradoException e) {
        ErrorResponse erro = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(AssentoNaoExistenteException.class)
    public ResponseEntity<Object> handleAssentoNaoExistenteException(AssentoNaoExistenteException e) {
        ErrorResponse erro = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
