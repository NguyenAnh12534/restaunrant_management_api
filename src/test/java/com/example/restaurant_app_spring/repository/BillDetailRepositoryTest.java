package com.example.restaurant_app_spring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.restaurant_app_spring.entity.BillOrder;
import com.example.restaurant_app_spring.entity.MenuItem;
import com.example.restaurant_app_spring.entity.billdetail.BillDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import java.util.Arrays;
import java.util.List;

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

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Order(1)
    public void testAddBillDetail() {
        MenuItem menuItem1 = new MenuItem();
        menuItem1.setName("test item 1");
        menuItem1.setPrice(12.0);

        MenuItem menuItem2 = new MenuItem();
        menuItem2.setName("test item 2");

        MenuItem menuItem = new MenuItem();
        menuItem.setName("Banh mi");
        menuItem.setPrice(15000.0);
        this.menuItemRepository.save(menuItem);

        BillOrder billOrder = new BillOrder();

        BillDetail billDetail = new BillDetail();
        billDetail.getId().setMenuItemId(1);
        billDetail.setMenuItem(menuItem);
        billOrder.setBillDetails(Arrays.asList(billDetail));

        this.billOrderRepository.save(billOrder);
        BillDetail savedBillDetail =this.billDetailRepository.save(billDetail);
        assertThat(savedBillDetail).isNotNull();
        assertThat(savedBillDetail.getMenuItem()).isEqualTo(menuItem);
        assertThat(savedBillDetail.getBillOrder()).isEqualTo(billOrder);
        System.out.println(this.billDetailRepository.findAll().size());
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