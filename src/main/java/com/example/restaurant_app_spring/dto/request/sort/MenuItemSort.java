package com.example.restaurant_app_spring.dto.request.sort;

import com.example.restaurant_app_spring.annotation.validator.SortingConstraint;
import com.example.restaurant_app_spring.entity.MenuItem;

public class MenuItemSort extends SortRequest
{
    @SortingConstraint(sortClass = MenuItem.class)
    private String sort;

    public void setSort(String sort) {
        super.setSORT(sort);
        this.sort = sort;
    }
}
