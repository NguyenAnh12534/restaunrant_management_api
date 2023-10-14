package com.example.restaurant_app_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.awt.print.Pageable;

@SpringBootApplication
public class RestaurantAppSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantAppSpringApplication.class, args);
    }

}
