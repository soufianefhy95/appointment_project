package com.maiia.pro.dto;

import com.maiia.pro.entity.Availability;
import com.maiia.pro.entity.TimeSlot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvailabilityDto {

    private Integer id;
    private Integer practitionerId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;


    public final static AvailabilityDto fromEntity(Availability availability) {
        if (availability == null) {
            return null;
        }

        return AvailabilityDto.builder()
                .practitionerId(availability.getPractitionerId())
                .startDate(availability.getStartDate())
                .endDate(availability.getEndDate())
                .build();
    }

    public final static Availability toEntity(AvailabilityDto availabilityDto) {
        if (availabilityDto == null) {
            return null;
        }

        Availability availability = new Availability();
        availability.setId(availabilityDto.getId());
        availability.setPractitionerId(availabilityDto.getPractitionerId());
        availability.setStartDate(availabilityDto.getStartDate());
        availability.setEndDate(availabilityDto.getEndDate());

        return availability;
    }
}
