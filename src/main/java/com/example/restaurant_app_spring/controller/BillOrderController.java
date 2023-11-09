package com.example.restaurant_app_spring.controller;

import com.example.restaurant_app_spring.dto.request.billdetail.BillDetailRequest;
import com.example.restaurant_app_spring.dto.request.billorder.BillOrderRequest;
import com.example.restaurant_app_spring.dto.request.billorder.BillOrderSort;
import com.example.restaurant_app_spring.dto.request.pagination.PaginationRequest;
import com.example.restaurant_app_spring.dto.response.SuccessResponse;
import com.example.restaurant_app_spring.dto.response.billorder.BillOrderResponse;
import com.example.restaurant_app_spring.dto.response.menuitem.MenuItemResponse;
import com.example.restaurant_app_spring.service.BillOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import java.net.URI;
import java.util.List;

import static com.example.restaurant_app_spring.constant.UrlConstant.BILL_ORDER_ENDPOINT;

@RestController
@RequestMapping(BILL_ORDER_ENDPOINT)
@RequiredArgsConstructor
public class BillOrderController {
    private final BillOrderService billOrderService;
    @GetMapping
    public ResponseEntity<SuccessResponse<List<BillOrderResponse>>> getAllBillOrder(@Valid PaginationRequest paginationRequest,
                                                                                    @Valid BillOrderSort billOrderSort) {
        List<BillOrderResponse> billOrderResponses = this.billOrderService.getAllBillOrder(paginationRequest, billOrderSort);
        return ResponseEntity.ok(SuccessResponse.of(billOrderResponses));
    }

    @GetMapping("{id}")
    public ResponseEntity<SuccessResponse<BillOrderResponse>> getBillOrderByID(@PathVariable Integer id) {
        BillOrderResponse billOrderResponse = this.billOrderService.getBillOrderById(id);
        return ResponseEntity.ok(SuccessResponse.of(billOrderResponse));
    }
    @PostMapping
    public ResponseEntity<URI> createBillOrder(@RequestBody BillOrderRequest billOrderRequest) {
        BillOrderResponse billOrderResponse = this.billOrderService.createBillOrder(billOrderRequest);
        HttpHeaders httpHeaders = new HttpHeaders();
        String resourceLocation = BILL_ORDER_ENDPOINT + "/" + billOrderResponse.getId();
        httpHeaders.set("Location", resourceLocation);

        return ResponseEntity.created(URI.create(BILL_ORDER_ENDPOINT + "/" + billOrderResponse.getId())).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<URI> updateBillOrder(@PathVariable Integer id, @RequestBody BillOrderRequest billOrderRequest) {
        BillOrderResponse billOrderResponse = this.billOrderService.updateBillOrder(id, billOrderRequest);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Location", BILL_ORDER_ENDPOINT + "/" + billOrderResponse.getId());

        return ResponseEntity.noContent().headers(httpHeaders).build();
    }

    @PostMapping("{id}/menu-items")
    public ResponseEntity<URI> addNewMenuItemToBill(@PathVariable Integer id, @RequestBody List<BillDetailRequest> billDetailRequests){
        BillOrderResponse billOrderResponse = this.billOrderService.addNewMenuItemToBill(id, billDetailRequests);
        HttpHeaders httpHeaders = new HttpHeaders();
        String resourceLocation = BILL_ORDER_ENDPOINT + "/" + billOrderResponse.getId();
        httpHeaders.set("Location", resourceLocation);

        return ResponseEntity.created(URI.create(resourceLocation)).headers(httpHeaders).build();
    }

    @DeleteMapping("{billId}/menu-items/{menuItemId}")
    public ResponseEntity<Void> deleteMenuItemFromBill(@PathVariable Integer billId, @PathVariable Integer menuItemId) {
        this.billOrderService.deleteMenuItemFromBill(billId, menuItemId);

        return ResponseEntity.noContent().build();
    }
}
