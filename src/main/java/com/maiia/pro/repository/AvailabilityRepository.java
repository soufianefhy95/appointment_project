package com.maiia.pro.repository;

import com.maiia.pro.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvailabilityRepository extends CrudRepository<Availability, String> {
    List<Availability> findByPractitionerId(Integer id);
    List<Availability> findAll();
}
