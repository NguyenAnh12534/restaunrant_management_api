package com.example.restaurant_app_spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class CustomerProfile {

    @Id
    @GeneratedValue
    @Column(name = "customer_profile_sk")
    private Integer id;

    private String name;
    private int age;
    private String gender;
    private String address;
    @OneToOne
    @JoinColumn(name = "customer_fk")
    private Customer customer;

}
