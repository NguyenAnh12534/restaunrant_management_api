package com.example.restaurant_app_spring.dto.request.menuitem;

import com.example.restaurant_app_spring.common.annotation.validator.SortingConstraint;
import com.example.restaurant_app_spring.dto.request.sort.SortRequest;
import com.example.restaurant_app_spring.entity.MenuItem;

public class MenuItemSort extends SortRequest
{
    @SortingConstraint(sortClass = MenuItem.class)
    private String sort;


    public void setSort(String sort) {
        super.setSort(sort);
        this.sort = sort;
    }
}
