package com.example.restaurant_app_spring.dto.response.menuitem;

import java.sql.Timestamp;

public class MenuItemResponse {
    public Integer id;
    public String name;
    public String description;
    public String image;
    public Double price;
    public Timestamp createdAt;
    public Timestamp updatedAt;
}
