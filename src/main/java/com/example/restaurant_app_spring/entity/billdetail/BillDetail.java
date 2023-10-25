package com.example.restaurant_app_spring.entity.billdetail;

import com.example.restaurant_app_spring.entity.BillOrder;
import com.example.restaurant_app_spring.entity.MenuItem;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Data
public class BillDetail {

    @EmbeddedId
    private BillDetailKey id = new BillDetailKey();

    @ManyToOne
    @MapsId("menuItemId")
    @JoinColumn(name="product_id")
    private MenuItem menuItem;

    @ManyToOne
    @MapsId("billId")
    @JoinColumn(name="customer_id")
    private BillOrder billOrder;

    private Double historicalPrice;
    private int quantity;
    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    public void setMenuItem(MenuItem menuItem) {
        if(this.menuItem != null)
            return;
        this.menuItem = menuItem;
        this.historicalPrice = menuItem.getPrice();
        this.id.setMenuItemId(menuItem.getId());
    }

    public void setBillOrder(BillOrder billOrder) {
        if(this.billOrder != null)
            return;
        this.billOrder = billOrder;
        this.id.setMenuItemId(billOrder.getId());
    }

}
