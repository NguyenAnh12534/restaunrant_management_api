package com.example.restaurant_app_spring.dto.request.sort;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort.Direction;

@Getter
@Setter
public class SortItem {
    private String fieldName;
    private Direction sortType;
}
