package com.example.restaurant_app_spring.controller;

import com.example.restaurant_app_spring.dto.request.menuitem.MenuItemCreateRequestDto;
import com.example.restaurant_app_spring.dto.request.menuitem.MenuItemFilter;
import com.example.restaurant_app_spring.dto.request.pagination.PaginationRequest;
import com.example.restaurant_app_spring.dto.request.sort.MenuItemSort;
import com.example.restaurant_app_spring.dto.response.menuitem.MenuItemResponseDto;
import com.example.restaurant_app_spring.entity.MenuItem;
import com.example.restaurant_app_spring.service.MenuItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.restaurant_app_spring.constant.UrlConstant.MENU_ITEM_ENDPOINT;

@RestController
@RequestMapping(MENU_ITEM_ENDPOINT)
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    @GetMapping
    public ResponseEntity<List<MenuItemResponseDto>> getAllMenuItems(@Valid MenuItemFilter menuItemFilter,
                                                                     @Valid PaginationRequest paginationRequest,
                                                                     @Valid MenuItemSort sort) {
        return ResponseEntity.ok(this.menuItemService
                .getAll(
                    menuItemFilter,
                    paginationRequest,
                    sort)
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<MenuItemResponseDto> getMenuItemById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.menuItemService.getById(id));
    }

    @PostMapping
    public ResponseEntity createMenuItems(@RequestBody MenuItemCreateRequestDto menuItemCreateRequestDto) {
        MenuItem createdMenuItem = this.menuItemService.create(menuItemCreateRequestDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Location", MENU_ITEM_ENDPOINT + "/" + createdMenuItem.getId());

        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).build();
    }


}
