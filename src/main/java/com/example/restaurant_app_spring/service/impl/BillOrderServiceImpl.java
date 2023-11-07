package com.example.restaurant_app_spring.service.impl;

import com.example.restaurant_app_spring.dto.request.billdetail.BillDetailRequest;
import com.example.restaurant_app_spring.dto.request.billorder.BillOrderRequest;
import com.example.restaurant_app_spring.dto.request.pagination.PaginationRequest;
import com.example.restaurant_app_spring.dto.request.sort.SortRequest;
import com.example.restaurant_app_spring.dto.response.billorder.BillOrderResponse;
import com.example.restaurant_app_spring.entity.BillOrder;
import com.example.restaurant_app_spring.entity.MenuItem;
import com.example.restaurant_app_spring.entity.billdetail.BillDetail;
import com.example.restaurant_app_spring.entity.billdetail.BillDetailKey;
import com.example.restaurant_app_spring.enums.BillOrderStatus;
import com.example.restaurant_app_spring.exception.InvalidDataException;
import com.example.restaurant_app_spring.mapper.BillDetailMapper;
import com.example.restaurant_app_spring.mapper.BillOrderMapper;
import com.example.restaurant_app_spring.repository.BillDetailRepository;
import com.example.restaurant_app_spring.repository.BillOrderRepository;
import com.example.restaurant_app_spring.repository.MenuItemRepository;
import com.example.restaurant_app_spring.service.BillOrderService;
import jakarta.transaction.NotSupportedException;
import jakarta.transaction.Transactional;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BillOrderServiceImpl implements BillOrderService {

    private final BillOrderRepository billOrderRepository;
    private final MenuItemRepository menuItemRepository;
    private final BillOrderMapper billOrderMapper;
    private final BillDetailMapper billDetailMapper;
    private final BillDetailRepository billDetailRepository;

    @Override
    public List<BillOrderResponse> getAllBillOrder(PaginationRequest paginationRequest, SortRequest sort) {
        PageRequest pageRequest = PageRequest.of(paginationRequest.getPageNumber(),  paginationRequest.getPageLimit(), Sort.by(sort.extractOrders()));
        List<BillOrder> billOrders = this.billOrderRepository.findAll(pageRequest).getContent();

        return this.billOrderMapper.convertToDtos(billOrders);
    }

    @Override
    public BillOrderResponse getBillOrderById(Integer id) {
        BillOrder billOrder = this.billOrderRepository.findById(id).orElseThrow();

        return this.billOrderMapper.convertToDto(billOrder);
    }

    @Override
    @Transactional
    public BillOrderResponse createBillOrder(BillOrderRequest billOrderRequest) {
        BillOrder billOrder = this.billOrderMapper.convertToEntityFromRequest(billOrderRequest);
        this.validateBillDetails(billOrder.getBillDetails());
        billOrder = this.billOrderRepository.save(billOrder);

        return this.billOrderMapper.convertToDto(billOrder);
    }

    @Override
    public BillOrderResponse updateBillOrder(Integer billOrderId, BillOrderRequest billOrderRequest) {
        BillOrder oldBillOrder = this.billOrderRepository.findById(billOrderId).orElseThrow();
        this.validateEditableBillOrder(oldBillOrder);

        BillOrder newBillOrder = this.billOrderMapper.convertToEntityFromRequest(billOrderRequest);
        this.validateBillDetails(newBillOrder.getBillDetails());

        oldBillOrder.load(newBillOrder);
        BillOrder updatedBillOrder = this.billOrderRepository.save(oldBillOrder);

        return this.billOrderMapper.convertToDto(updatedBillOrder);
    }

    @Override
    public BillOrderResponse addNewMenuItemToBill(Integer billOrderId, List<BillDetailRequest> billDetailRequests) {
        BillOrder billOrder = this.billOrderRepository.findById(billOrderId).orElseThrow();
        this.validateEditableBillOrder(billOrder);

        List<BillDetail> newBillDetail = this.billDetailMapper.convertToEntityFromRequests(billDetailRequests);
        this.validateBillDetails(newBillDetail);

        billOrder.addBillDetails(newBillDetail);
        billOrder = this.billOrderRepository.save(billOrder);

        return this.billOrderMapper.convertToDto(billOrder);
    }

    @Override
    public void deleteMenuItemFromBill(Integer billOrderId, Integer menuItemId) {
        BillOrder billOrder = this.billOrderRepository.findById(billOrderId).orElseThrow();
        this.validateEditableBillOrder(billOrder);

        if(!billOrder.containsMenuItem(menuItemId)) {
            throw new NoSuchElementException();
        }

        this.billDetailRepository.deleteByMenuItemId(menuItemId);
    }

    protected void validateBillDetail(BillDetail billDetailToValidate) {
        try {
            MenuItem menuItem = this.menuItemRepository.findById(billDetailToValidate.getId().getMenuItemId()).orElseThrow();
            billDetailToValidate.setMenuItem(menuItem);
        } catch (NoSuchElementException noSuchElementException) {
            InvalidDataException invalidDataException = new InvalidDataException();
            invalidDataException.getErrors().put("menuItemId", billDetailToValidate.getId().getMenuItemId().toString());

            throw invalidDataException;
        }
    }

    protected void validateBillDetails(List<BillDetail> billDetails) {
        InvalidDataException invalidDataExceptionWrapper = new InvalidDataException();
        for (BillDetail billDetail : billDetails) {
            try {
                this.validateBillDetail(billDetail);
            } catch (InvalidDataException invalidDataException) {
                invalidDataExceptionWrapper.load(invalidDataException);
            }
        }

        if (!invalidDataExceptionWrapper.getErrors().isEmpty()) {
            throw invalidDataExceptionWrapper;
        }
    }

    protected void validateEditableBillOrder(BillOrder billOrder) {
        if(!BillOrderStatus.getEditableStatuses().contains(billOrder.getStatus())) {
            InvalidDataException invalidDataException = new InvalidDataException();
            invalidDataException.setMESSAGE("Bill is not editable");
            throw invalidDataException;
        }
    }
}
