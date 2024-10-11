package com.brokerconnect.brokerai.service;

import com.brokerconnect.brokerai.model.Broker;
import com.brokerconnect.brokerai.repository.BrokerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrokerService {
    @Autowired
    private BrokerRepository brokerRepository;

    public Broker addBroker(Broker broker) {
        return brokerRepository.save(broker);
    }

    public Broker getBrokerById(Long id) {
        return brokerRepository.findById(id).orElse(null);
    }

    public List<Broker> getAllBrokers() {
        return brokerRepository.findAll();
    }

    public Broker updateBroker(Broker updatedBroker) {
        return brokerRepository.save(updatedBroker);
    }

    public void deleteBroker(Long id) {
        brokerRepository.deleteById(id);
    }
}
