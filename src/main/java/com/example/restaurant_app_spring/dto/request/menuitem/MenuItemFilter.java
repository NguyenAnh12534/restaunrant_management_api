package com.example.restaurant_app_spring.dto.request.menuitem;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MenuItemFilter {

    public String name;
}
