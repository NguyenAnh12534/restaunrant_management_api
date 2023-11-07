package com.example.restaurant_app_spring.dto.request.billorder;

import com.example.restaurant_app_spring.dto.request.billdetail.BillDetailRequest;
import com.example.restaurant_app_spring.enums.BillOrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BillOrderRequest {
    private BillOrderStatus status = BillOrderStatus.UN_PAID;
    private List<BillDetailRequest> billDetails = new ArrayList<>();
}
