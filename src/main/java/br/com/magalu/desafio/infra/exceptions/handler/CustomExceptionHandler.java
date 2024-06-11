package br.com.magalu.desafio.infra.exceptions.handler;

import br.com.magalu.desafio.implementation.util.MessagesException;
import br.com.magalu.desafio.infra.exceptions.*;
import br.com.magalu.desafio.infra.exceptions.details.ExceptionDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.Date;

/**
 * @author Pedro Henrique Veiga
 * @created 05/06/24
 * @lastModified 05/06/24
 */
@RestControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    MessagesException resourceNotFound = MessagesException.RESOURCE_NOT_FOUND;
    MessagesException someInvalidField = MessagesException.SOME_INVALID_FIELD;
    MessagesException fileNotFound = MessagesException.NOT_FOUND_FILE;




    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionDetails> handleBadRequestException(BadRequestException bad) {
        Date date = new Date();
        ExceptionDetails badDetails = ExceptionDetails.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title(someInvalidField.getValue())
                .methodService(bad.getStackTrace()[0].getMethodName())
                .message(bad.getMessage())
                .build();
        return new ResponseEntity<>(badDetails, HttpStatus.BAD_REQUEST);
    }




}
