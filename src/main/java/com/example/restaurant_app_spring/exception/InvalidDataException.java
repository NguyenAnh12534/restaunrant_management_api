package com.example.restaurant_app_spring.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class InvalidDataException extends RuntimeException{
    private HashMap<String, String> errors = new HashMap<>();
    private final String MESSAGE = "Input data is not valid";


}
