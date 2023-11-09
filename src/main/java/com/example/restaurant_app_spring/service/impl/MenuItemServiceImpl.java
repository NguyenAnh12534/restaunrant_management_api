package com.example.restaurant_app_spring.service.impl;

import com.example.restaurant_app_spring.dto.request.menuitem.MenuItemRequest;
import com.example.restaurant_app_spring.dto.request.menuitem.MenuItemFilter;
import com.example.restaurant_app_spring.dto.request.pagination.PaginationRequest;
import com.example.restaurant_app_spring.dto.request.sort.SortRequest;
import com.example.restaurant_app_spring.dto.response.menuitem.MenuItemResponse;
import com.example.restaurant_app_spring.entity.MenuItem;
import com.example.restaurant_app_spring.entity.billdetail.BillDetail;
import com.example.restaurant_app_spring.exception.InvalidDataException;
import com.example.restaurant_app_spring.mapper.MenuItemMapper;
import com.example.restaurant_app_spring.repository.BillDetailRepository;
import com.example.restaurant_app_spring.repository.MenuItemRepository;
import com.example.restaurant_app_spring.service.MenuItemService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final MenuItemMapper menuItemMapper;
    private final BillDetailRepository billDetailRepository;


    public MenuItemResponse create(MenuItemRequest menuItemRequest) {
        MenuItem menuItem = this.menuItemMapper.convertToEntityFromRequest(menuItemRequest);
        this.menuItemRepository.save(menuItem);

        return this.menuItemMapper.convertToDto(menuItem);
    }

    public MenuItemResponse getById(int id) {
        MenuItem menuItem = this.menuItemRepository.findById(id).orElseThrow();
        MenuItemResponse menuItemResponse = menuItemMapper.convertToDto(menuItem);

        return menuItemResponse;
    }

    public List<MenuItemResponse> getAll(MenuItemFilter filter, PaginationRequest paginationRequest, SortRequest sort) {
        PageRequest pageRequest = PageRequest.of(paginationRequest.getPageNumber(),  paginationRequest.getPageLimit(), Sort.by(sort.extractOrders()));
        MenuItem probe = this.menuItemMapper.convertToEntityFromFilter(filter);
        ExampleMatcher matcher = ExampleMatcher.matchingAny() .withMatcher("name",  new ExampleMatcher.GenericPropertyMatcher().contains());

        List<MenuItem> menuItems = this.menuItemRepository.findAll(Example.of(probe, matcher), pageRequest).getContent();
        return menuItemMapper.convertToDtos(menuItems);
    }

    @Override
    public MenuItemResponse update(Integer idToUpdate, MenuItemRequest menuItemUpdateRequest) {
        MenuItem menuItemToUpdate = this.menuItemRepository.findById(idToUpdate).orElseThrow();
        menuItemToUpdate.load(this.menuItemMapper.convertToEntityFromRequest(menuItemUpdateRequest));
        this.menuItemRepository.save(menuItemToUpdate);

        return this.menuItemMapper.convertToDto(menuItemToUpdate);
    }

    @Override
    public MenuItemResponse updatePartial(Integer idToUpdate, MenuItemRequest menuItemUpdateRequest) {
        MenuItem menuItemToUpdate = this.menuItemRepository.findById(idToUpdate).orElseThrow();
        menuItemToUpdate.loadNonNullFields(this.menuItemMapper.convertToEntityFromRequest(menuItemUpdateRequest));
        this.menuItemRepository.save(menuItemToUpdate);

        return this.menuItemMapper.convertToDto(menuItemToUpdate);
    }

    @Override
    public void delete(Integer idToDelete) {
        MenuItem menuItemToDelete = this.menuItemRepository.findById(idToDelete).orElseThrow();
        List<BillDetail> billDetails = this.billDetailRepository.findBillDetailByMenuItemId(idToDelete);
        if(billDetails.size() > 0) {
            throw new InvalidDataException();
        }
        this.menuItemRepository.delete(menuItemToDelete);
    }
}
