package com.example.restaurant_app_spring.entity.billdetail;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class BillDetailKey implements Serializable {
    @Column(name = "menu_item_id")
    private Integer menuItemId;

    @Column(name = "bill_id")
    private Integer billId;
}
