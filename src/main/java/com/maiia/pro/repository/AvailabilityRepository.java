package com.maiia.pro.repository;

import com.maiia.pro.entity.Availability;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AvailabilityRepository extends MongoRepository<Availability, String> {
    List<Availability> findByPractitionerId(String id);

    void deleteByPractitionerId(String practitionerId);
}
