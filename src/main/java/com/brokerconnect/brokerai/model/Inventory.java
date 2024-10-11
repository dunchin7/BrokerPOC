package com.brokerconnect.brokerai.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "broker_id")
    private Broker broker;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
    private List<Property> properties;
}
