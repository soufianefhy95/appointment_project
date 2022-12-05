package com.maiia.pro.controller;

import com.maiia.pro.dto.AvailabilityDto;
import com.maiia.pro.entity.Availability;
import com.maiia.pro.service.ProAvailabilityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/availabilities", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProAvailabilityController {
    @Autowired
    private ProAvailabilityService proAvailabilityService;

    @ApiOperation(value = "Get availabilities by practitionerId")
    @GetMapping
    public List<AvailabilityDto> getAvailabilities(@RequestParam final Integer practitionerId) {
        return proAvailabilityService.findByPractitionerId(practitionerId);
    }

    @ApiOperation(value = "Generate availabilities by practitionerId")
    @PostMapping
    public List<AvailabilityDto> generateAvailabilities(@RequestParam final Integer practitionerId) {
        return proAvailabilityService.generateAvailabilities(practitionerId);
    }
}
