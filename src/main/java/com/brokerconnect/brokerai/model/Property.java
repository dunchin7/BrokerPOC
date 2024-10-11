package com.brokerconnect.brokerai.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private double price;
    private int bedrooms;
    private int bathrooms;
    private double squareFootage;
    private String propertyType;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;
}
