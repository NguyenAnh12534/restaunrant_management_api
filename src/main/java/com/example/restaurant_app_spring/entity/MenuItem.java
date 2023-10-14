package com.example.restaurant_app_spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "items")
public class MenuItem {

    @Id
    @GeneratedValue
    @Column(name = "item_pk")
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "menuItems")
    private Set<BillOrder> billOrders;
}
