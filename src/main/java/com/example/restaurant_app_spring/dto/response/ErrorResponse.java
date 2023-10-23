package com.example.restaurant_app_spring.dto.response;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponse {
    public String message;
    public Map<String, String> errors = new HashMap<>();
}
