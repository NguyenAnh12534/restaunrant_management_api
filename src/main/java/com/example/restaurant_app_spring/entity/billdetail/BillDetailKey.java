package com.example.restaurant_app_spring.entity.billdetail;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class BillDetailKey implements Serializable {
    @Column(name = "menu_item_id")
    private Integer menuItemId;

    @Column(name = "bill_id")
    private Integer billId;
}
