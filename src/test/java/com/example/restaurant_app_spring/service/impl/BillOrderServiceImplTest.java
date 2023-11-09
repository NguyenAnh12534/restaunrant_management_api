package com.example.restaurant_app_spring.service.impl;

import com.example.restaurant_app_spring.dto.request.billdetail.BillDetailRequest;
import com.example.restaurant_app_spring.dto.request.billorder.BillOrderRequest;
import com.example.restaurant_app_spring.entity.MenuItem;
import com.example.restaurant_app_spring.repository.BillDetailRepository;
import com.example.restaurant_app_spring.repository.BillOrderRepository;
import com.example.restaurant_app_spring.repository.MenuItemRepository;
import com.example.restaurant_app_spring.service.BillOrderService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class BillOrderServiceImplTest {

    @Autowired
    private BillOrderService billOrderService;
    @Autowired
    private BillOrderRepository billOrderRepository;

    @Autowired
    private BillDetailRepository billDetailRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void createBillOrder() {
        MenuItem menuItem1 = new MenuItem();
        menuItem1.setName("test item 1");
        menuItem1.setPrice(12.0);

        MenuItem menuItem2 = new MenuItem();
        menuItem2.setName("test item 2");
        menuItem2.setPrice(12.0);


        menuItem1 = this.menuItemRepository.save(menuItem1);
        menuItem2 = this.menuItemRepository.save(menuItem2);
        boolean checking = this.entityManager.contains(menuItem2);

        BillDetailRequest billDetailRequest1 = new BillDetailRequest();
        billDetailRequest1.quantity = 5;
        billDetailRequest1.menuItemId = menuItem1.getId();

        BillDetailRequest billDetailRequest2 = new BillDetailRequest();
        billDetailRequest2.quantity = 10;
        billDetailRequest2.menuItemId = menuItem2.getId();

        List<BillDetailRequest> billDetailRequests = Arrays.asList(billDetailRequest1, billDetailRequest2);

        BillOrderRequest billOrderRequest  = new BillOrderRequest();

        billOrderRequest.setBillDetails(billDetailRequests);

        this.billOrderService.createBillOrder(billOrderRequest);

        assertThat(this.billOrderRepository.findAll().size()).isGreaterThan(0);
        this.billDetailRepository.findAll().forEach(billDetail -> {
            System.out.println(billDetail);
        });
    }
}