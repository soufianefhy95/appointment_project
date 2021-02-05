package com.maiia.pro.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class Practitioner {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String speciality;
}
