package com.brokerconnect.brokerai.service;

import com.brokerconnect.brokerai.model.Broker;
import com.brokerconnect.brokerai.model.Lead;
import com.brokerconnect.brokerai.model.LeadStatus;
import com.brokerconnect.brokerai.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeadService {
    @Autowired
    private LeadRepository leadRepository;

    public Lead addLead(Lead lead) {
        return leadRepository.save(lead);
    }

    public Lead getLeadById(Long id) {
        return leadRepository.findById(id).orElse(null);
    }

    public List<Lead> getLeadsByBroker(Broker broker) {
        return leadRepository.findAll().stream()
                .filter(lead -> lead.getBroker().equals(broker))
                .collect(Collectors.toList());
    }

    public Lead updateLeadStatus(Lead lead, LeadStatus newStatus) {
        lead.setStatus(newStatus);
        return leadRepository.save(lead);
    }

    public List<Lead> getLeadsByStatus(LeadStatus status) {
        return leadRepository.findAll().stream()
                .filter(lead -> lead.getStatus() == status)
                .collect(Collectors.toList());
    }
}
