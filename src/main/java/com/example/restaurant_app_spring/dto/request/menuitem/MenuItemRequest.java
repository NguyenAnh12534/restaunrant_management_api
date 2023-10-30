package com.example.restaurant_app_spring.dto.request.menuitem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class MenuItemRequest {
    @NotBlank
    private String name;
    private String description;
    private MultipartFile image;

    @Min(0)
    private Double price;
}
