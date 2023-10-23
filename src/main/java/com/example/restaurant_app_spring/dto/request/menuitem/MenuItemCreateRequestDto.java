package com.example.restaurant_app_spring.dto.request.menuitem;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItemCreateRequestDto {
    @NotBlank
    private String name;
}
