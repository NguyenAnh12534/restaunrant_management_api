package com.example.restaurant_app_spring.mapper;

import com.example.restaurant_app_spring.dto.request.billdetail.BillDetailRequest;
import com.example.restaurant_app_spring.dto.response.billdetail.BillDetailResponse;
import com.example.restaurant_app_spring.entity.billdetail.BillDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = MenuItemMapper.class)
public interface BillDetailMapper extends BaseMapper<BillDetailRequest, BillDetail, BillDetailResponse> {

    @Mapping(source = "menuItemId", target = "id.menuItemId")
    BillDetail convertToEntityFromRequest(BillDetailRequest dto);
}
