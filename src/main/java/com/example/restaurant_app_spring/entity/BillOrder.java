package com.example.restaurant_app_spring.entity;

import com.example.restaurant_app_spring.entity.billdetail.BillDetail;
import com.example.restaurant_app_spring.enums.BillOrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = {"billDetails"})
public class BillOrder {

    @Id
    @GeneratedValue
    private Integer id;

    private BillOrderStatus status = BillOrderStatus.UN_PAID;

    @OneToMany(mappedBy = "billOrder", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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

    public void addBillDetail(BillDetail billDetail) {
        if(billDetail == null)
            return;
        this.billDetails.add(billDetail);
        billDetail.setBillOrder(this);
    }

    public void addBillDetails(List<BillDetail> billDetailsToAdd) {
        if(billDetailsToAdd == null)
            return;
        for(BillDetail billDetail : billDetailsToAdd)  {
            this.addBillDetail(billDetail);
        }
    }

    public void setBillDetails(List<BillDetail> billDetails) {
        this.billDetails = billDetails;
        billDetails.forEach(billDetail -> {
            billDetail.setBillOrder(this);
        });
    }

    public void load(BillOrder billOrder) {
        if(billOrder == null)
            return;
        this.status = billOrder.status;
        this.updateBillDetails(billOrder.getBillDetails());
    }

    public void updateBillDetails(List<BillDetail> billDetails) {
        for(BillDetail newBillDetail : billDetails) {
            this.billDetails.forEach(billDetail -> {
                if(billDetail.getMenuItem().equals(newBillDetail.getMenuItem())) {
                    billDetail.load(newBillDetail);
                }
            });
        }
    }

    public boolean containsMenuItem(Integer menuItemId) {
        return this.billDetails.stream().anyMatch(billDetail -> {
            return billDetail.getMenuItem().getId().equals(menuItemId);
        });
    }

    public void removeMenuItem(Integer menuItemId) {
        Iterator<BillDetail> billDetailIterator = this.billDetails.iterator();
        while(billDetailIterator.hasNext()) {
            BillDetail nextBillDetail = billDetailIterator.next();
            if(nextBillDetail.getMenuItem().getId().equals(menuItemId)) {
                billDetailIterator.remove();
            }
        }
    }
}
