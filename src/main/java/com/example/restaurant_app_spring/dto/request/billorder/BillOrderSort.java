package com.example.restaurant_app_spring.dto.request.billorder;

import com.example.restaurant_app_spring.common.annotation.validator.SortingConstraint;
import com.example.restaurant_app_spring.dto.request.sort.SortRequest;
import com.example.restaurant_app_spring.entity.BillOrder;

public class BillOrderSort extends SortRequest {
    @SortingConstraint(sortClass = BillOrder.class)
    private String sort;

    public void setSort(String sort) {
        super.setSort(sort);
        this.sort = sort;
    }
}
