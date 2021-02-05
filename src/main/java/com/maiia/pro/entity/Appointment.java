package com.maiia.pro.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document
public class Appointment {
    private String id;
    private String patientId;
    private String practitionerId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
