package com.maiia.pro.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Availability {
    private String id;
    private String practitionerId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
