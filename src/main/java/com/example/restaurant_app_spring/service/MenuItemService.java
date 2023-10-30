package com.example.restaurant_app_spring.service;

import com.example.restaurant_app_spring.dto.request.menuitem.MenuItemRequest;
import com.example.restaurant_app_spring.dto.request.menuitem.MenuItemFilter;
import com.example.restaurant_app_spring.dto.request.pagination.PaginationRequest;
import com.example.restaurant_app_spring.dto.request.sort.SortRequest;
import com.example.restaurant_app_spring.dto.response.menuitem.MenuItemResponse;

import java.util.List;

public interface MenuItemService {
    MenuItemResponse create(MenuItemRequest menuItemRequest);

    List<MenuItemResponse> getAll(MenuItemFilter filter, PaginationRequest paginationRequest, SortRequest sort);
    MenuItemResponse getById(int id);
    MenuItemResponse update(Integer idToUpdate, MenuItemRequest menuItemUpdateRequest);
    MenuItemResponse updatePartial(Integer idToUpdate, MenuItemRequest menuItemUpdateRequest);
    MenuItemResponse delete(Integer idToDelete);
}
