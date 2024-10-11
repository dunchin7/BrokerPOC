package com.brokerconnect.brokerai.repository;

import com.brokerconnect.brokerai.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadRepository extends JpaRepository<Lead, Long> {
}
