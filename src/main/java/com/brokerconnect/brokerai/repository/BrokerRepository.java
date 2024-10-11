package com.brokerconnect.brokerai.repository;

import com.brokerconnect.brokerai.model.Broker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrokerRepository extends JpaRepository<Broker, Long> {
}
