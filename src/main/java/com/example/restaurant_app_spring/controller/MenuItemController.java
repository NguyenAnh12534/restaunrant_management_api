package com.example.restaurant_app_spring.controller;

import com.example.restaurant_app_spring.dto.request.menuitem.MenuItemCreateRequest;
import com.example.restaurant_app_spring.dto.request.menuitem.MenuItemFilter;
import com.example.restaurant_app_spring.dto.request.pagination.PaginationRequest;
import com.example.restaurant_app_spring.dto.request.sort.MenuItemSort;
import com.example.restaurant_app_spring.dto.response.SuccessResponse;
import com.example.restaurant_app_spring.dto.response.menuitem.MenuItemResponse;
import com.example.restaurant_app_spring.entity.MenuItem;
import com.example.restaurant_app_spring.service.MenuItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.restaurant_app_spring.constant.UrlConstant.MENU_ITEM_ENDPOINT;

@RestController
@RequestMapping(MENU_ITEM_ENDPOINT)
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<MenuItemResponse>>> getAllMenuItems(@Valid MenuItemFilter menuItemFilter,
                                                           @Valid PaginationRequest paginationRequest,
                                                           @Valid MenuItemSort sort) {
        List<MenuItemResponse> menuItems = this.menuItemService.getAll(
                        menuItemFilter,
                        paginationRequest,
                        sort);

        return ResponseEntity.ok(SuccessResponse.of(menuItems, "Successfully fetched all menu items"));
    }

    @GetMapping("{id}")
    public ResponseEntity<SuccessResponse<MenuItemResponse>> getMenuItemById(@PathVariable Integer id) {
        MenuItemResponse menuItem = this.menuItemService.getById(id);
        return ResponseEntity.ok(SuccessResponse.of(menuItem, "Successfully fetched one menu item"));
    }

    @PostMapping
    public ResponseEntity createMenuItems(@ModelAttribute MenuItemCreateRequest menuItemCreateRequest) {
        System.out.println(menuItemCreateRequest.getImage().getOriginalFilename());

        MenuItem createdMenuItem = this.menuItemService.create(menuItemCreateRequest);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Location", MENU_ITEM_ENDPOINT + "/" + createdMenuItem.getId());

        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).build();
    }


}
