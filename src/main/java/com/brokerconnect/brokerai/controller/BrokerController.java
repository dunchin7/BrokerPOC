package com.brokerconnect.brokerai.controller;

import com.brokerconnect.brokerai.model.Broker;
import com.brokerconnect.brokerai.service.BrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brokers")
public class BrokerController {

    @Autowired
    private BrokerService brokerService;

    @PostMapping
    public ResponseEntity<Broker> addBroker(@RequestBody Broker broker) {
        return ResponseEntity.ok(brokerService.addBroker(broker));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Broker> getBroker(@PathVariable Long id) {
        Broker broker = brokerService.getBrokerById(id);
        if (broker != null) {
            return ResponseEntity.ok(broker);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Broker>> getAllBrokers() {
        return ResponseEntity.ok(brokerService.getAllBrokers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Broker> updateBroker(@PathVariable Long id, @RequestBody Broker broker) {
        broker.setId(id);
        return ResponseEntity.ok(brokerService.updateBroker(broker));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBroker(@PathVariable Long id) {
        brokerService.deleteBroker(id);
        return ResponseEntity.ok().build();
    }
}
