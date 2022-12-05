package com.maiia.pro.dto;

import com.maiia.pro.entity.TimeSlot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlotDto {

    private Integer id;
    private Integer practitionerId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;


    public final static TimeSlotDto fromEntity(TimeSlot timeSlot) {
        if (timeSlot == null) {
            return null;
        }

        return TimeSlotDto.builder()
                .practitionerId(timeSlot.getPractitionerId())
                .startDate(timeSlot.getStartDate())
                .endDate(timeSlot.getEndDate())
                .build();
    }

    public final static TimeSlot toEntity(TimeSlotDto timeSlotDto) {
        if (timeSlotDto == null) {
            return null;
        }

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setId(timeSlotDto.getId());
        timeSlot.setPractitionerId(timeSlotDto.getPractitionerId());
        timeSlot.setStartDate(timeSlotDto.getStartDate());
        timeSlot.setEndDate(timeSlotDto.getEndDate());

        return timeSlot;
    }
}
