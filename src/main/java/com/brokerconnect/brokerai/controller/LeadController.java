package com.brokerconnect.brokerai.controller;

import com.brokerconnect.brokerai.model.Broker;
import com.brokerconnect.brokerai.model.Lead;
import com.brokerconnect.brokerai.model.LeadStatus;
import com.brokerconnect.brokerai.service.BrokerService;
import com.brokerconnect.brokerai.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    @Autowired
    private LeadService leadService;

    @Autowired
    private BrokerService brokerService;

    @PostMapping
    public ResponseEntity<Lead> addLead(@RequestBody Lead lead) {
        return ResponseEntity.ok(leadService.addLead(lead));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lead> getLead(@PathVariable Long id) {
        Lead lead = leadService.getLeadById(id);
        if (lead != null) {
            return ResponseEntity.ok(lead);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/broker/{brokerId}")
    public ResponseEntity<List<Lead>> getLeadsByBroker(@PathVariable Long brokerId) {
        Broker broker = brokerService.getBrokerById(brokerId);
        if (broker != null) {
            return ResponseEntity.ok(leadService.getLeadsByBroker(broker));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Lead> updateLeadStatus(@PathVariable Long id, @RequestParam LeadStatus status) {
        Lead lead = leadService.getLeadById(id);
        if (lead != null) {
            return ResponseEntity.ok(leadService.updateLeadStatus(lead, status));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Lead>> getLeadsByStatus(@PathVariable LeadStatus status) {
        return ResponseEntity.ok(leadService.getLeadsByStatus(status));
    }
}
