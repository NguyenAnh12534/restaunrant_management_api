package com.example.restaurant_app_spring.exception.handler;

import com.example.restaurant_app_spring.constant.ErrorMessageConstant;
import com.example.restaurant_app_spring.dto.response.ErrorResponse;
import com.example.restaurant_app_spring.exception.InvalidDataException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class DtoExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({InvalidDataException.class})
    public ResponseEntity<ErrorResponse> handleInvalidInputException(InvalidDataException exception){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.message = ErrorMessageConstant.INPUT_NOT_VALID_MESSAGE;
        errorResponse.errors = exception.getErrors();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            FieldError fieldError = (FieldError) error;
            String fieldName = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();

            errors.put(fieldName, errorMessage);
        });

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.message = ErrorMessageConstant.INPUT_NOT_VALID_MESSAGE;
        errorResponse.errors = errors;

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
