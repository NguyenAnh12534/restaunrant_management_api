package com.example.restaurant_app_spring.service.impl;

import com.example.restaurant_app_spring.dto.request.menuitem.MenuItemCreateRequest;
import com.example.restaurant_app_spring.dto.request.menuitem.MenuItemFilter;
import com.example.restaurant_app_spring.dto.request.pagination.PaginationRequest;
import com.example.restaurant_app_spring.dto.request.sort.SortRequest;
import com.example.restaurant_app_spring.dto.response.menuitem.MenuItemResponse;
import com.example.restaurant_app_spring.entity.MenuItem;
import com.example.restaurant_app_spring.mapper.MenuItemMapper;
import com.example.restaurant_app_spring.repository.MenuItemRepository;
import com.example.restaurant_app_spring.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final MenuItemMapper menuItemMapper;

    public MenuItem create(MenuItemCreateRequest menuItemCreateRequest) {
        MenuItem menuItem = this.menuItemMapper.convertToEntityFromCreateDto(menuItemCreateRequest);
        System.out.println(menuItem);
        this.menuItemRepository.save(menuItem);
        return menuItem;
    }

    public MenuItemResponse getById(int id) {
        MenuItem menuItem = this.menuItemRepository.findById(id).orElseThrow();
        MenuItemResponse menuItemResponse = menuItemMapper.convertToDto(menuItem);
        return menuItemResponse;
    }

    public List<MenuItemResponse> getAll(MenuItemFilter filter, PaginationRequest paginationRequest, SortRequest sort) {

        PageRequest pageRequest = PageRequest.of(paginationRequest.getPageNumber(),  paginationRequest.getPageLimit(), Sort.by(sort.extractOrders()));
        MenuItem probe = this.menuItemMapper.convertToEntityFromFilter(filter);
        List<MenuItem> menuItems = this.menuItemRepository.findAll(Example.of(probe), pageRequest).getContent();

        return menuItemMapper.convertToDtos(menuItems);
    }
}
