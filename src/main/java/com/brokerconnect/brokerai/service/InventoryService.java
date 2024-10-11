package com.brokerconnect.brokerai.service;

import com.brokerconnect.brokerai.model.Broker;
import com.brokerconnect.brokerai.model.Inventory;
import com.brokerconnect.brokerai.model.Property;
import com.brokerconnect.brokerai.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public Inventory addInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    public List<Inventory> getInventoriesByBroker(Broker broker) {
        return inventoryRepository.findAll().stream()
                .filter(inventory -> inventory.getBroker().equals(broker))
                .collect(Collectors.toList());
    }

    public void addPropertyToInventory(Property property, Inventory inventory) {
        inventory.getProperties().add(property);
        inventoryRepository.save(inventory);
    }

    public void removePropertyFromInventory(Property property, Inventory inventory) {
        inventory.getProperties().remove(property);
        inventoryRepository.save(inventory);
    }

    public List<Property> getPropertiesByInventory(Inventory inventory) {
        return inventory.getProperties();
    }
}
