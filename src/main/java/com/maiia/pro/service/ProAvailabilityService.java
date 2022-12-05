package com.maiia.pro.service;


import com.maiia.pro.dto.AvailabilityDto;
import com.maiia.pro.dto.TimeSlotDto;
import com.maiia.pro.entity.Appointment;
import com.maiia.pro.entity.Availability;
import com.maiia.pro.entity.Practitioner;
import com.maiia.pro.exception.EntityNotFoundException;
import com.maiia.pro.exception.ErrorCodes;
import com.maiia.pro.repository.AppointmentRepository;
import com.maiia.pro.repository.AvailabilityRepository;
import com.maiia.pro.repository.PractitionerRepository;
import com.maiia.pro.repository.TimeSlotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProAvailabilityService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PractitionerRepository practitionerRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    public List<AvailabilityDto> findByPractitionerId(Integer practitionerId) {
        return availabilityRepository.findByPractitionerId(practitionerId)
                .stream()
                .map(AvailabilityDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<AvailabilityDto> generateAvailabilities(Integer practitionerId) {

        if (practitionerId == null) {
            throw new EntityNotFoundException(
                    "practitioner ID is NULL ",
                    ErrorCodes.PRACTITIONER_NOT_FOUND
            );
        }
        LocalDateTime currentdate = LocalDateTime.now();
        LocalDateTime startDate = LocalDateTime.of(currentdate.getYear(), currentdate.getMonth(), currentdate.getDayOfMonth(), currentdate.getHour(), currentdate.getMinute(), currentdate.getSecond());

        // check practitioner if it exists
        Optional<Practitioner> practitioner = practitionerRepository.findById(practitionerId);

        if (practitioner.isEmpty()) {
            log.error("Practitioner with ID {} was not found in DB", practitionerId);
            throw new EntityNotFoundException(
                    "Practitioner with ID {} was not found in DB " + practitionerId,
                    ErrorCodes.PRACTITIONER_NOT_FOUND
            );
        }

        // before we generate appointment we need to get the last appointment for the given pracitioner
        // and increase a 15 min, because every appointment is = 15 min
        List<Appointment> appointments = appointmentRepository.findByPractitionerId(practitionerId);
        Appointment appointment = null;
        if (appointments.isEmpty()) {
            log.info("No appointments were found for this practitioner : " + practitionerId);
        } else {
            appointment = appointments.stream().max(Comparator.comparing(Appointment::getStartDate)).get();
        }
        TimeSlotDto dto = null;
        if (appointment == null) {
            dto = TimeSlotDto.builder()
                    .practitionerId(practitionerId)
                    .startDate(startDate.plusMinutes(15))
                    .endDate(startDate.plusMinutes(75))
                    .build();
        } else {
            dto = TimeSlotDto.builder()
                    .practitionerId(practitionerId)
                    .startDate(appointment.getStartDate().plusMinutes(15))
                    .endDate(appointment.getStartDate().plusMinutes(75))
                    .build();
        }

        // save the time slots for a given practitioner
        timeSlotRepository.save(TimeSlotDto.toEntity(dto));

        ArrayList<AvailabilityDto> availabilityDtos = new ArrayList<>();
        LocalDateTime startDate_ = startDate;
        if (appointment != null) {
            // increase the last appointment of the practitioner by 15 min
            startDate_ = appointment.getStartDate().plusMinutes(15);
        }

        // generate availability for next hour
        for (int i = 0; i < 4; i++) {
            LocalDateTime endDate = startDate_.plusMinutes(15);
            AvailabilityDto av_dto = AvailabilityDto.builder()
                    .practitionerId(practitionerId)
                    .startDate(startDate_)
                    .endDate(endDate)
                    .build();
            startDate_ = endDate;
            availabilityDtos.add(av_dto);
        }

        List<AvailabilityDto> availabilities = availabilityDtos.stream()
                .map(AvailabilityDto::toEntity)
                .map(availabilityRepository::save)
                .map(AvailabilityDto::fromEntity)
                .collect(Collectors.toList());

        return availabilities;
    }
}
