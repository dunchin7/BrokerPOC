package com.brokerconnect.brokerai.controller;

import com.brokerconnect.brokerai.model.Broker;
import com.brokerconnect.brokerai.model.Inventory;
import com.brokerconnect.brokerai.model.Property;
import com.brokerconnect.brokerai.service.BrokerService;
import com.brokerconnect.brokerai.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private BrokerService brokerService;

    @PostMapping
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory) {
        return ResponseEntity.ok(inventoryService.addInventory(inventory));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventory(@PathVariable Long id) {
        Inventory inventory = inventoryService.getInventoryById(id);
        if (inventory != null) {
            return ResponseEntity.ok(inventory);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/broker/{brokerId}")
    public ResponseEntity<List<Inventory>> getInventoriesByBroker(@PathVariable Long brokerId) {
        Broker broker = brokerService.getBrokerById(brokerId);
        if (broker != null) {
            return ResponseEntity.ok(inventoryService.getInventoriesByBroker(broker));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{inventoryId}/properties")
    public ResponseEntity<Void> addPropertyToInventory(@PathVariable Long inventoryId, @RequestBody Property property) {
        Inventory inventory = inventoryService.getInventoryById(inventoryId);
        if (inventory != null) {
            inventoryService.addPropertyToInventory(property, inventory);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{inventoryId}/properties/{propertyId}")
    public ResponseEntity<Void> removePropertyFromInventory(@PathVariable Long inventoryId, @PathVariable Long propertyId) {
        Inventory inventory = inventoryService.getInventoryById(inventoryId);
        if (inventory != null) {
            Property property = inventory.getProperties().stream()
                    .filter(p -> p.getId().equals(propertyId))
                    .findFirst()
                    .orElse(null);
            if (property != null) {
                inventoryService.removePropertyFromInventory(property, inventory);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{inventoryId}/properties")
    public ResponseEntity<List<Property>> getPropertiesByInventory(@PathVariable Long inventoryId) {
        Inventory inventory = inventoryService.getInventoryById(inventoryId);
        if (inventory != null) {
            return ResponseEntity.ok(inventoryService.getPropertiesByInventory(inventory));
        }
        return ResponseEntity.notFound().build();
    }
}
