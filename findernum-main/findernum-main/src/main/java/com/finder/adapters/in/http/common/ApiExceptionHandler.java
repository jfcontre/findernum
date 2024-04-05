package com.finder.adapters.in.http.common;

import com.finder.application.service.OutOfRangeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//Indicate that this class assitt a controller class and can have a body in response
public class ApiExceptionHandler {
    @ExceptionHandler(OutOfRangeException.class)
    public ResponseEntity<StandardizedApiExceptionResponse> handleOutOfRangeException(OutOfRangeException ex) {
        StandardizedApiExceptionResponse response = new StandardizedApiExceptionResponse("Error de rango permitido", "1021", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardizedApiExceptionResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        StandardizedApiExceptionResponse response = new StandardizedApiExceptionResponse("Error de validación", "1021", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
