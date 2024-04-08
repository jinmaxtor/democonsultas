package com.maxtorgroup.democonsultas.api.controller;

import com.maxtorgroup.democonsultas.domain.contract.DoctorService;
import com.maxtorgroup.democonsultas.domain.contract.PatientService;
import com.maxtorgroup.democonsultas.domain.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final PatientService patientService;

    public DoctorController(DoctorService doctorService, PatientService patientService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<PageDto<DoctorDto>> getAllDoctors(@ModelAttribute PageRequestDto pageRequest) {
        return new ResponseEntity<>(doctorService.getPageableDoctorsByName(pageRequest), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable Long id) {
        return new ResponseEntity<>(doctorService.getDoctorById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DoctorDto> createDoctor(@RequestBody DoctorRegisterDto doctor) {
        return new ResponseEntity<>(doctorService.createDoctor(doctor), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDto> updateDoctor(@PathVariable Long id, @RequestBody DoctorRegisterDto doctor) {
        return new ResponseEntity<>(doctorService.updateDoctor(id, doctor), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDoctor(@PathVariable Long id) {
        return new ResponseEntity<>(doctorService.deleteDoctor(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/patients")
    public ResponseEntity<List<PatientDto>> getPatients(@PathVariable Long id) {
        return new ResponseEntity<>(patientService.getPatientsByDoctorId(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/medical-consultations")
    public ResponseEntity<List<MedicalConsultationDto>> getMedicalConsultations(@PathVariable Long id) {
        return new ResponseEntity<>(doctorService.getMedicalConsultations(id), HttpStatus.OK);
    }

    @PostMapping("/medical-consultations")
    public ResponseEntity<MedicalConsultationDto> createMedicalConsultation(@RequestBody MedicalConsultationDto medicalConsultation) {
        return new ResponseEntity<>(doctorService.createMedicalConsultation(medicalConsultation), HttpStatus.CREATED);
    }

    @PutMapping("/medical-consultations/{id}")
    public ResponseEntity<MedicalConsultationDto> updateMedicalConsultation(@PathVariable Long id, @RequestBody MedicalConsultationDto medicalConsultation) {
        return new ResponseEntity<>(doctorService.updateMedicalConsultation(id, medicalConsultation), HttpStatus.OK);
    }

    @DeleteMapping("/medical-consultations/{id}")
    public ResponseEntity<Boolean> deleteMedicalConsultation(@PathVariable Long id) {
        return new ResponseEntity<>(doctorService.deleteMedicalConsultation(id), HttpStatus.OK);
    }
}
