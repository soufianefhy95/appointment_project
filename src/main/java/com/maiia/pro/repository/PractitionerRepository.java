package com.maiia.pro.repository;

import com.maiia.pro.entity.Practitioner;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PractitionerRepository extends MongoRepository<Practitioner, String> {
}
