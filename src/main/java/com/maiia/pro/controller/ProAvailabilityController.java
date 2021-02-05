package com.maiia.pro.controller;

import com.maiia.pro.entity.Availability;
import com.maiia.pro.service.ProAvailabilityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/availability", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProAvailabilityController {
    @Autowired
    private ProAvailabilityService proAvailabilityService;

    @ApiOperation(value = "Get availabilities by practitionerId")
    @GetMapping("/{practitionerId}")
    public List<Availability> getAvailabilities(@PathVariable final String practitionerId) {
        return proAvailabilityService.findByPractitionerId(practitionerId);
    }

}
