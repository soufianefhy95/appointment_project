package com.maiia.pro.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
