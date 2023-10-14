package com.example.restaurant_app_spring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.Set;

@Entity
public class BillOrder {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_fk")
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "bill_order_detail",
            joinColumns = {@JoinColumn(name = "bill_order_fk")},
            inverseJoinColumns = {@JoinColumn(name = "menu_item_fk")}
    )
    private Set<MenuItem> menuItems;
}
