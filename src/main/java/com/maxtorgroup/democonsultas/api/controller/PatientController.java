package com.maxtorgroup.democonsultas.api.controller;

import com.maxtorgroup.democonsultas.domain.contract.PatientService;
import com.maxtorgroup.democonsultas.domain.dto.MedicalConsultationDto;
import com.maxtorgroup.democonsultas.domain.dto.PatientDto;
import com.maxtorgroup.democonsultas.domain.dto.PatientRegisterDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        return new ResponseEntity<>(patientService.getPatients(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable Long id) {
        return new ResponseEntity<>(patientService.getPatientById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientRegisterDto patient) {
        return new ResponseEntity<>(patientService.createPatient(patient), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable Long id, @RequestBody PatientRegisterDto patient) {
        return new ResponseEntity<>(patientService.updatePatient(id, patient), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePatient(@PathVariable Long id) {
        return new ResponseEntity<>(patientService.deletePatient(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/medical-consultations")
    public ResponseEntity<List<MedicalConsultationDto>> getMedicalConsultations(@PathVariable Long id) {
        return new ResponseEntity<>(patientService.getMedicalConsultations(id), HttpStatus.OK);
    }
}
