package com.maiia.pro.dto;

import com.maiia.pro.entity.Appointment;
import com.maiia.pro.entity.Availability;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {

    private Integer id;
    private Integer patientId;
    private Integer practitionerId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public final static AppointmentDto fromEntity(Appointment appointment) {
        if (appointment == null) {
            return null;
        }

        return AppointmentDto.builder()
                .patientId(appointment.getPatientId())
                .practitionerId(appointment.getPractitionerId())
                .startDate(appointment.getStartDate())
                .endDate(appointment.getEndDate())
                .build();
    }

    public final static Appointment toEntity(AppointmentDto appointmentDto) {
        if (appointmentDto == null) {
            return null;
        }

        Appointment appointment = new Appointment();
        appointment.setId(appointmentDto.getId());
        appointment.setPatientId(appointmentDto.getPatientId());
        appointment.setPractitionerId(appointmentDto.getPractitionerId());
        appointment.setStartDate(appointmentDto.getStartDate());
        appointment.setEndDate(appointmentDto.getEndDate());

        return appointment;
    }
}
