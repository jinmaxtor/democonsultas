package com.maxtorgroup.democonsultas.application.service;

import com.maxtorgroup.democonsultas.domain.contract.PatientRepository;
import com.maxtorgroup.democonsultas.domain.contract.PatientService;
import com.maxtorgroup.democonsultas.domain.dto.PatientDto;
import com.maxtorgroup.democonsultas.domain.dto.PatientRegisterDto;
import com.maxtorgroup.democonsultas.infrastructure.entity.Patient;
import com.maxtorgroup.democonsultas.infrastructure.mapper.EntityMapper;

import java.util.List;

public class ApplicationPatientService implements PatientService {

    private final EntityMapper mapper;
    private final PatientRepository patientRepository;

    public ApplicationPatientService(EntityMapper mapper, PatientRepository patientRepository) {
        this.mapper = mapper;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<PatientDto> getPatients() {
        return mapper.patientsToDto(patientRepository.getPatients());
    }

    @Override
    public PatientDto getPatientById(Long id) {
        return mapper.toDto(patientRepository.getPatientById(id).orElse(null));
    }

    @Override
    public PatientDto createPatient(PatientRegisterDto patient) {
        validateExistingPatientByEmail(patient);

        Patient data = mapper.toEntity(patient);
        return mapper.toDto(patientRepository.savePatient(data));
    }

    @Override
    public PatientDto updatePatient(Long id, PatientRegisterDto patient) {
        validateExistingPatientById(id);

        Patient data = mapper.toEntity(patient);
        data.setId(id);
        return mapper.toDto(patientRepository.savePatient(data));
    }

    @Override
    public Boolean deletePatient(Long id) {
        return patientRepository.deletePatientById(id);
    }

    private void validateExistingPatientById(Long id) {
        Patient existingPatient = patientRepository.getPatientById(id).orElse(null);

        if (existingPatient == null) {
            throw new IllegalArgumentException("Patient with id " + id + " not found");
        }
    }

    private void validateExistingPatientByEmail(PatientRegisterDto patient) {
        Patient existingPatient = patientRepository
                .getPatientByEmail(patient.getEmail())
                .orElse(null);

        if (existingPatient != null) {
            throw new IllegalArgumentException("Patient with email " + patient.getEmail() + " already exists");
        }
    }
}
