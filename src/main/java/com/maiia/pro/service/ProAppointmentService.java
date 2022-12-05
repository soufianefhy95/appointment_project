package com.maiia.pro.service;

import com.maiia.pro.dto.AppointmentDto;
import com.maiia.pro.entity.Appointment;
import com.maiia.pro.entity.Availability;
import com.maiia.pro.entity.Patient;
import com.maiia.pro.entity.Practitioner;
import com.maiia.pro.exception.EntityNotFoundException;
import com.maiia.pro.exception.ErrorCodes;
import com.maiia.pro.repository.AppointmentRepository;
import com.maiia.pro.repository.AvailabilityRepository;
import com.maiia.pro.repository.PatientRepository;
import com.maiia.pro.repository.PractitionerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProAppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PractitionerRepository practitionerRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    public Appointment find(Integer appointmentId) {
        return appointmentRepository.findById(appointmentId).orElseThrow();
    }

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> findByPractitionerId(Integer practitionerId) {
        checkPractitioner(practitionerId);
        return appointmentRepository.findByPractitionerId(practitionerId);
    }

    public void checkPractitioner(Integer practitionerId) {
        Optional<Practitioner> practitioner = practitionerRepository.findById(practitionerId);

        if (practitioner.isEmpty()) {
            log.error("Practitioner with ID {} was not found in DB", practitionerId);
            throw new EntityNotFoundException(
                    "Practitioner with ID {} was not found in DB " + practitionerId,
                    ErrorCodes.PRACTITIONER_NOT_FOUND
            );
        }
    }

    public void checkPatient(Integer patientId) {
        Optional<Patient> patient = patientRepository.findById(patientId);

        if (patient.isEmpty()) {
            log.error("Patient with ID {} was not found in DB", patientId);
            throw new EntityNotFoundException(
                    "Patient with ID {} was not found in DB " + patientId,
                    ErrorCodes.PATIENT_NOT_FOUND
            );
        }
    }

    public AppointmentDto generateAppointment(AppointmentDto dto) {
        checkPractitioner(dto.getPractitionerId());
        checkPatient(dto.getPatientId());

        // update availabilities of the given practitioner
        List<Availability> availabilities = availabilityRepository.findByPractitionerId(dto.getPractitionerId());
        if (availabilities.isEmpty()) {
            log.error("No availabilities were found");
            throw new EntityNotFoundException(
                    "No availabilities were found",
                    ErrorCodes.AVAILABILITY_NOT_FOUND
            );
        }
        Availability availability = availabilities.stream()
                .filter(a -> a.getStartDate().isEqual(dto.getStartDate()))
                .findFirst()
                .orElse(null);
        // remove the availability because it will be reserved for this patient
        availabilityRepository.delete(availability);

        // generate the appointment
        AppointmentDto appointmentDto = AppointmentDto.builder()
                .patientId(dto.getPatientId())
                .practitionerId(dto.getPractitionerId())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();

        return AppointmentDto.fromEntity(
                appointmentRepository.save(AppointmentDto.toEntity(appointmentDto))
        );
    }

}
