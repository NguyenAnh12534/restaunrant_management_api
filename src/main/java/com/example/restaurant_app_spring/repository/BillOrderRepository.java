package com.example.restaurant_app_spring.repository;

import com.example.restaurant_app_spring.entity.BillOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillOrderRepository extends JpaRepository<BillOrder, Integer> {
}
