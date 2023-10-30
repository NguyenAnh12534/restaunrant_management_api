package com.example.restaurant_app_spring.mapper;

import com.example.restaurant_app_spring.dto.request.menuitem.MenuItemRequest;
import com.example.restaurant_app_spring.dto.request.menuitem.MenuItemFilter;
import com.example.restaurant_app_spring.dto.response.menuitem.MenuItemResponse;
import com.example.restaurant_app_spring.entity.MenuItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = MultipartMapper.class)
public interface MenuItemMapper extends BaseMapper<MenuItemRequest, MenuItem, MenuItemResponse>{
    MenuItem convertToEntityFromFilter(MenuItemFilter menuItemFilter);
}
