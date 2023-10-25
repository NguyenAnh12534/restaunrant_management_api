package com.example.restaurant_app_spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "customer_sk")
    private Integer id;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @OneToOne(mappedBy = "customer")
    private CustomerProfile profile;

    @OneToMany(mappedBy = "customer")
    private List<BillOrder> orders = new ArrayList<>();

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

}
