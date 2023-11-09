package com.example.restaurant_app_spring.entity.billdetail;

import com.example.restaurant_app_spring.entity.BillOrder;
import com.example.restaurant_app_spring.entity.MenuItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterJoinTable;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Data
@ToString(exclude = "billOrder")
public class BillDetail {

    @EmbeddedId
    private BillDetailKey id = new BillDetailKey();

    @ManyToOne
    @MapsId("menuItemId")
    @JoinColumn(name="menu_item_id")
    private MenuItem menuItem;

    @ManyToOne
    @MapsId("billId")
    @JoinColumn(name="bill_id")
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
    }

    public void setBillOrder(BillOrder billOrder) {
        if(this.billOrder != null)
            return;
        this.billOrder = billOrder;
    }

    public void load(BillDetail billDetail) {
        if(billDetail == null)
            return;
        this.quantity = billDetail.quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BillDetail that)) return false;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
