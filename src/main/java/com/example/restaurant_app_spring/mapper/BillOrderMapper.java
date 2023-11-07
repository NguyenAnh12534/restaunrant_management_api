package com.example.restaurant_app_spring.mapper;

import com.example.restaurant_app_spring.dto.request.billorder.BillOrderRequest;
import com.example.restaurant_app_spring.dto.response.billorder.BillOrderResponse;
import com.example.restaurant_app_spring.entity.BillOrder;
import com.example.restaurant_app_spring.entity.billdetail.BillDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = BillDetailMapper.class)
public interface BillOrderMapper extends BaseMapper<BillOrderRequest, BillOrder, BillOrderResponse> {
}
