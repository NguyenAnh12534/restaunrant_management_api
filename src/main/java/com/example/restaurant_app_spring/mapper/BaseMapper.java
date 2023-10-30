package com.example.restaurant_app_spring.mapper;

import java.util.List;

public interface BaseMapper<REQUEST_DTO, ENTITY, RESPONSE_DTO> {
    ENTITY convertToEntityFromRequest(REQUEST_DTO dto);
    List<RESPONSE_DTO> convertToDtos(List<ENTITY> dto);
    RESPONSE_DTO convertToDto(ENTITY entity);
}
