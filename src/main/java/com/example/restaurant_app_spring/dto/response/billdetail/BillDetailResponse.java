package com.example.restaurant_app_spring.dto.response.billdetail;

import com.example.restaurant_app_spring.dto.response.menuitem.MenuItemResponse;
import com.example.restaurant_app_spring.entity.BillOrder;
import com.example.restaurant_app_spring.entity.MenuItem;
import com.example.restaurant_app_spring.entity.billdetail.BillDetailKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
public class BillDetailResponse {
    MenuItemResponse menuItem;
    private Double historicalPrice;
    private int quantity;
    private Timestamp createdAt;

    private Timestamp updatedAt;
}
