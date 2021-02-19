package com.maiia.pro.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document
public class TimeSlot {
    @Id
    private String id;
    private String practitionerId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
