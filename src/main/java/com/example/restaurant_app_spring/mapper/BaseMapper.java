package com.example.restaurant_app_spring.mapper;

import java.util.List;

public interface BaseMapper<REQUEST_DTO, ENTITY, RESPONSE_DTO> {
    ENTITY convertToEntityFromRequest(REQUEST_DTO dto);
    List<ENTITY> convertToEntityFromRequests(List<REQUEST_DTO> dtos);
    List<RESPONSE_DTO> convertToDtos(List<ENTITY> dto);
    RESPONSE_DTO convertToDto(ENTITY entity);
}
