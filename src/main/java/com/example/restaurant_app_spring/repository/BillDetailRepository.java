package com.example.restaurant_app_spring.repository;

import com.example.restaurant_app_spring.entity.billdetail.BillDetail;
import com.example.restaurant_app_spring.entity.billdetail.BillDetailKey;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, BillDetailKey> {
    @Modifying
    @Transactional
    @Query("delete from BillDetail b where b.menuItem.id=:menuItemId")
    void deleteByMenuItemId(@Param("menuItemId") Integer menuItemId);

}
