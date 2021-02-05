package com.maiia.pro.service;

import com.maiia.pro.entity.Appointment;
import com.maiia.pro.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProAppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment find(String appointmentId) {
        return appointmentRepository.findById(appointmentId).orElseThrow();
    }

    public List<Appointment> findByPractitionerId(String practitionerId) {
        return appointmentRepository.findByPractitionerId(practitionerId);
    }

    public Appointment insert(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Appointment appointment) {
        appointmentRepository.delete(appointment);
    }

}
