package com.example.restaurant_app_spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "types")
@Data
public class Type {
    @Id
    @GeneratedValue
    @Column(name = "type_pk")
    private Integer id;

    private String typeName;

    @ManyToMany
    @JoinTable(
            name = "type_item",
            joinColumns = {@JoinColumn(name = "type_fk")},
            inverseJoinColumns = {@JoinColumn(name = "menu_item_fk")}
    )
    private List<MenuItem> menuItems = new ArrayList<>();

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
}
