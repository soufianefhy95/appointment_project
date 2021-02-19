package com.maiia.pro.repository;

import com.maiia.pro.entity.TimeSlot;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TimeSlotRepository extends MongoRepository<TimeSlot, String> {
    List<TimeSlot> findByPractitionerId(String practitionerId);
}
