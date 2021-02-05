package com.maiia.pro.repository;

import com.maiia.pro.entity.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRepository extends MongoRepository<Patient, String> {
}
