package com.example.restaurant_app_spring.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.restaurant_app_spring.dto.response.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GetRequestExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NoSuchElementException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.message = exception.getMessage();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
