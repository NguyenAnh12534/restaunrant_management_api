package com.example.restaurant_app_spring.service;

import com.example.restaurant_app_spring.dto.request.menuitem.MenuItemCreateRequest;
import com.example.restaurant_app_spring.dto.request.menuitem.MenuItemFilter;
import com.example.restaurant_app_spring.dto.request.pagination.PaginationRequest;
import com.example.restaurant_app_spring.dto.request.sort.SortRequest;
import com.example.restaurant_app_spring.dto.response.menuitem.MenuItemResponse;
import com.example.restaurant_app_spring.entity.MenuItem;

import java.util.List;

public interface MenuItemService {
    MenuItem create(MenuItemCreateRequest menuItemCreateRequest);

    List<MenuItemResponse> getAll(MenuItemFilter filter, PaginationRequest paginationRequest, SortRequest sort);
    MenuItemResponse getById(int id);
}
