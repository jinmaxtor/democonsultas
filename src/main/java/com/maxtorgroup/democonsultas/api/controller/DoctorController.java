package com.maxtorgroup.democonsultas.api.controller;

import com.maxtorgroup.democonsultas.domain.contract.DoctorService;
import com.maxtorgroup.democonsultas.domain.dto.DoctorDto;
import com.maxtorgroup.democonsultas.domain.dto.DoctorRegisterDto;
import com.maxtorgroup.democonsultas.domain.dto.MedicalConsultationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<Iterable<DoctorDto>> getAllDoctors() {
        return new ResponseEntity<>(doctorService.getDoctors(), HttpStatus.OK);
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

    @GetMapping("/{id}/medical-consultation")
    public ResponseEntity<List<MedicalConsultationDto>> getMedicalConsultations(@PathVariable Long id) {
        return new ResponseEntity<>(doctorService.getMedicalConsultations(id), HttpStatus.OK);
    }

    @PostMapping("/medical-consultation")
    public ResponseEntity<MedicalConsultationDto> createMedicalConsultation(@RequestBody MedicalConsultationDto medicalConsultation) {
        return new ResponseEntity<>(doctorService.createMedicalConsultation(medicalConsultation), HttpStatus.CREATED);
    }

    @PutMapping("/medical-consultation/{id}")
    public ResponseEntity<MedicalConsultationDto> updateMedicalConsultation(@PathVariable Long id, @RequestBody MedicalConsultationDto medicalConsultation) {
        return new ResponseEntity<>(doctorService.updateMedicalConsultation(id, medicalConsultation), HttpStatus.OK);
    }

    @DeleteMapping("/medical-consultation/{id}")
    public ResponseEntity<Boolean> deleteMedicalConsultation(@PathVariable Long id) {
        return new ResponseEntity<>(doctorService.deleteMedicalConsultation(id), HttpStatus.OK);
    }
}
