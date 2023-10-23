package com.example.restaurant_app_spring.mapper;

import java.util.List;

public interface BaseMapper<ENTITY, DTO> {
    ENTITY convertToEntity(DTO dto);
    List<DTO> convertToDtos(List<ENTITY> dto);
     DTO convertToDto(ENTITY entity);
}
