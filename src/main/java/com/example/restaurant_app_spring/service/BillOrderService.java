package com.example.restaurant_app_spring.service;

import com.example.restaurant_app_spring.dto.request.billdetail.BillDetailRequest;
import com.example.restaurant_app_spring.dto.request.billorder.BillOrderRequest;
import com.example.restaurant_app_spring.dto.request.pagination.PaginationRequest;
import com.example.restaurant_app_spring.dto.request.sort.SortRequest;
import com.example.restaurant_app_spring.dto.response.billorder.BillOrderResponse;

import java.util.List;

public interface BillOrderService {
    List<BillOrderResponse> getAllBillOrder(PaginationRequest paginationRequest, SortRequest sort);
    BillOrderResponse getBillOrderById(Integer id);
    BillOrderResponse createBillOrder(BillOrderRequest billOrderRequest);
    BillOrderResponse updateBillOrder(Integer billOrderId, BillOrderRequest billOrderRequest);
    BillOrderResponse addNewMenuItemToBill(Integer billOrderId, List<BillDetailRequest> billDetailRequests);
    void deleteMenuItemFromBill(Integer billOrderId, Integer menuItemId);
}
