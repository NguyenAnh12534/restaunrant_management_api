package com.example.restaurant_app_spring.entity;

import com.example.restaurant_app_spring.entity.billdetail.BillDetail;
import com.example.restaurant_app_spring.enums.BillOrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class BillOrder {

    @Id
    @GeneratedValue
    private Integer id;

    private BillOrderStatus status = BillOrderStatus.UN_PAID;
    @ManyToOne
    @JoinColumn(name = "customer_fk")
    private Customer customer;

    @OneToMany(mappedBy = "billOrder")
    private List<BillDetail> billDetails = new ArrayList<>();

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    public double calculateTotalPrice() {
        if (billDetails.size() == 0) {
            return 0;
        }

        double totalPrice = 0;
        for(BillDetail billDetail : billDetails) {
            totalPrice += billDetail.getHistoricalPrice() * billDetail.getQuantity();
        }

        return totalPrice;
    }

}
