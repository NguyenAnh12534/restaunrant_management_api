package com.example.restaurant_app_spring.dto.request.sort;

import com.example.restaurant_app_spring.annotation.validator.SortingConstraint;
import com.example.restaurant_app_spring.entity.MenuItem;
import jakarta.validation.constraints.Email;

public class MenuItemSort extends SortRequest
{
    @SortingConstraint(sortClass = MenuItem.class)
    private String sort;


    public void setSort(String sort) {
        super.setSort(sort);
        this.sort = sort;
    }
}
