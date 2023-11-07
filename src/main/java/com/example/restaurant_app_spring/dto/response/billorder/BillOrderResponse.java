package com.example.restaurant_app_spring.dto.response.billorder;

import com.example.restaurant_app_spring.dto.response.billdetail.BillDetailResponse;
import com.example.restaurant_app_spring.entity.billdetail.BillDetail;
import com.example.restaurant_app_spring.enums.BillOrderStatus;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BillOrderResponse {

    private Integer id;

    private BillOrderStatus status;

    private List<BillDetailResponse> billDetails;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
