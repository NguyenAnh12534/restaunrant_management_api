package com.example.restaurant_app_spring.mapper;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class MultipartMapper {
    public String asString(MultipartFile file) {
        if(file == null)
            return null;
        return file.getOriginalFilename();
    }
    public MultipartMapper(){

    }
}
