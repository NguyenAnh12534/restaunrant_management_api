package com.example.restaurant_app_spring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.restaurant_app_spring.entity.BillOrder;
import com.example.restaurant_app_spring.entity.MenuItem;
import com.example.restaurant_app_spring.entity.billdetail.BillDetail;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BillDetailRepositoryTest {

    @Autowired
    private BillOrderRepository billOrderRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private BillDetailRepository billDetailRepository;

    @Test
    @Order(1)
    public void testAddBillDetail() {
        System.out.println("Second");
        MenuItem menuItem = new MenuItem();
        menuItem.setName("Banh mi");
        menuItem.setPrice(15000.0);

        this.menuItemRepository.save(menuItem);

        BillOrder billOrder = new BillOrder();
        this.billOrderRepository.save(billOrder);

        BillDetail billDetail = new BillDetail();
        billDetail.setBillOrder(billOrder);
        billDetail.setMenuItem(menuItem);

        BillDetail savedBillDetail =this.billDetailRepository.save(billDetail);
        assertThat(savedBillDetail).isNotNull();
        assertThat(savedBillDetail.getMenuItem()).isEqualTo(menuItem);
        assertThat(savedBillDetail.getBillOrder()).isEqualTo(billOrder);
    }
    @Test
    @Order(2)
    public void testListAll() {
        System.out.println("First");
        List<BillDetail> billDetails = this.billDetailRepository.findAll();
        assertThat(billDetails.size()).isGreaterThan(0);
        billDetails.forEach(System.out::println);
    }
}