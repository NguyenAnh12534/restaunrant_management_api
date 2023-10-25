package com.example.restaurant_app_spring.repository;

import com.example.restaurant_app_spring.entity.BillOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillOrderRepository extends JpaRepository<BillOrder, Integer> {
}
