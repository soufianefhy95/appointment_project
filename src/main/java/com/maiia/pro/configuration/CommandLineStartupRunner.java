package com.maiia.pro.configuration;

import com.maiia.pro.entity.Patient;
import com.maiia.pro.entity.Practitioner;
import com.maiia.pro.entity.TimeSlot;
import com.maiia.pro.repository.PatientRepository;
import com.maiia.pro.repository.PractitionerRepository;
import com.maiia.pro.repository.TimeSlotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class CommandLineStartupRunner implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(CommandLineStartupRunner.class);

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PractitionerRepository practitionerRepository;
    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Override
    public void run(String... args) throws Exception {
        //initialise data
        for(int i=1;i<=5;i++){
            //create patient
            Patient patient = Patient.builder().id("patient_id_"+i).firstName("patient_"+i).lastName("maiia").build();
            //create practitioner
            Practitioner practitioner = Practitioner.builder().id("practitioner_id_"+i).firstName("practitioner"+i).lastName("maiia").build();
            //create timeSlots for practitioner
            List<TimeSlot> timeSlotList= new ArrayList<>();
            //timeslot from 2021/02/08 at 8H to 2021/02/08 at 12H
            TimeSlot timeSlot1= TimeSlot.builder().practitionerId("practitioner_id_"+i).startDate(LocalDateTime.of(2021,2,8,8,0))
                    .endDate(LocalDateTime.of(2021,2,8,12,0)).build();
            //timeslot from 2021/02/08 at 14H to 2021/02/08 at 17H
            TimeSlot timeSlot2= TimeSlot.builder().practitionerId("practitioner_id_"+i).startDate(LocalDateTime.of(2021,2,8,14,0))
                    .endDate(LocalDateTime.of(2021,2,8,17,0)).build();
            //timeslot from 2021/02/09 at 9H to 2021/02/09 at 17H
            TimeSlot timeSlot3= TimeSlot.builder().practitionerId("practitioner_id_"+i).startDate(LocalDateTime.of(2021,2,9,9,0))
                    .endDate(LocalDateTime.of(2021,2,9,17,0)).build();
            timeSlotList.addAll(Arrays.asList(timeSlot1,timeSlot2,timeSlot3));
            if(i%2==0){
                //timeslot from 2021/02/10 at 9H to 2021/02/10 at 16H
                TimeSlot timeSlot4= TimeSlot.builder().practitionerId("practitioner_id_"+i).startDate(LocalDateTime.of(2021,2,10,9,0))
                        .endDate(LocalDateTime.of(2021,2,10,16,0)).build();
                timeSlotList.add(timeSlot4);
            }
            if(i==3){
                //timeslot from 2021/02/11 at 11H to 2021/02/11 at 18H
                TimeSlot timeSlot5= TimeSlot.builder().practitionerId("practitioner_id_"+i).startDate(LocalDateTime.of(2021,2,11,11,0))
                        .endDate(LocalDateTime.of(2021,2,11,18,0)).build();
                timeSlotList.add(timeSlot5);
            }
            patientRepository.save(patient);
            practitionerRepository.save(practitioner);
            timeSlotRepository.saveAll(timeSlotList);
        }
        log.info("------------------created patients---------------- " +patientRepository.findAll());
        log.info("------------------created practitioners---------------- " +practitionerRepository.findAll());
        log.info("------------------created timeSlots---------------- " +timeSlotRepository.findAll());
    }
}
