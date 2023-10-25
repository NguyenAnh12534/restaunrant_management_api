package com.example.restaurant_app_spring.dto.response;

import java.util.HashMap;

public class SuccessResponse<T> {
    public String message;
    public T data;

    public static <T> SuccessResponse<T> of(T data) {
        SuccessResponse<T> successResponse = new SuccessResponse<>();
        successResponse.data = data;
        return successResponse;
    }

    public static <T> SuccessResponse<T> of(T data, String message) {
        SuccessResponse<T> successResponse = of(data);
        successResponse.message = message;
        return successResponse;
    }
}
