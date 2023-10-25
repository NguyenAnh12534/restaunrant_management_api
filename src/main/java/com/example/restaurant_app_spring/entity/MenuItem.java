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
@Table(name = "items")
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
    @ManyToMany(mappedBy = "menuItems")
    private List<Type> types = new ArrayList<>();
    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
}
