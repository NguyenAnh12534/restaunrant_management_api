package com.example.restaurant_app_spring.repository;

import com.example.restaurant_app_spring.entity.billdetail.BillDetail;
import com.example.restaurant_app_spring.entity.billdetail.BillDetailKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillDetailRepository extends JpaRepository<BillDetail, BillDetailKey> {
}
