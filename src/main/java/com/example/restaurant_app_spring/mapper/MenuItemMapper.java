package com.example.restaurant_app_spring.mapper;

import com.example.restaurant_app_spring.dto.request.menuitem.MenuItemCreateRequestDto;
import com.example.restaurant_app_spring.dto.response.menuitem.MenuItemResponseDto;
import com.example.restaurant_app_spring.entity.MenuItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import javax.swing.text.html.parser.Entity;

@Mapper(componentModel = "spring")
public abstract class MenuItemMapper implements BaseMapper<MenuItem, MenuItemResponseDto>{
    public abstract MenuItem convertToEntityFromCreateDto(MenuItemCreateRequestDto menuItemCreateRequestDto);
}
