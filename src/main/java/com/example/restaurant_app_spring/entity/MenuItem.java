package com.example.restaurant_app_spring.entity;

import com.example.restaurant_app_spring.entity.billdetail.BillDetail;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "menu_items")
@Data
public class MenuItem {

    @Id
    @GeneratedValue
    @Column(name = "item_pk")
    private Integer id;
    private String name;
    private String description;
    private String image;
    private Double price;
    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    public void load(MenuItem menuItem) {
        if(menuItem == null)
            return;

        this.name = menuItem.getName();
        this.description = menuItem.getDescription();
        this.price = menuItem.getPrice();
        this.image = menuItem.image;
    }

    public void loadNonNullFields(MenuItem menuItem) {
        if(menuItem == null)
            return;

        if(menuItem.name != null)
            this.name = menuItem.name;

        if(menuItem.description != null)
            this.description = menuItem.description;

        if(menuItem.price != null)
            this.price = menuItem.price;

        if(menuItem.image != null)
            this.image = menuItem.image;
    }
}
