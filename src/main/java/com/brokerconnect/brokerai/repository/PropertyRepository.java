package com.brokerconnect.brokerai.repository;

import com.brokerconnect.brokerai.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
