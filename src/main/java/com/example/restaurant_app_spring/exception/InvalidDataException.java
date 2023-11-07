package com.example.restaurant_app_spring.exception;

import com.example.restaurant_app_spring.constant.ErrorMessageConstant;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class InvalidDataException extends RuntimeException{
    private HashMap<String, String> errors = new HashMap<>();
    private String MESSAGE = ErrorMessageConstant.INPUT_NOT_VALID_MESSAGE;

    public void load(InvalidDataException invalidDataException) {
        if(invalidDataException == null)
            return;
        for(Map.Entry<String, String> error : invalidDataException.errors.entrySet()) {
            if(this.errors.containsKey(error.getKey())) {
                this.errors.merge(error.getKey(), error.getValue(), (oldError, newError) -> {
                   return oldError + ", " + newError;
                });
            }
        }
    }
}
