package com.maiia.pro.repository;

import com.maiia.pro.entity.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    List<Appointment> findByPractitionerId(String practitionerId);
    List<Appointment> findByPractitionerIdAndStartDateBeforeAndEndDateAfter(String practitionerId, LocalDateTime startDateTimeSlot,LocalDateTime endDateTimeSlot);
}
