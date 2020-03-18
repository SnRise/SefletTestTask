package ru.seflet.chat.controller.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.seflet.chat.exception.NoSuchResourceException;

@ControllerAdvice
public class RestControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchResourceException.class)
    public ResponseEntity<Object> handleNoSuchResourceException() {
        return new ResponseEntity<>("Exception", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
