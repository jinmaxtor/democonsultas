package com.maxtorgroup.democonsultas.application.service;

import com.maxtorgroup.democonsultas.domain.contract.MedicalConsultationRepository;
import com.maxtorgroup.democonsultas.domain.contract.PatientRepository;
import com.maxtorgroup.democonsultas.domain.contract.PatientService;
import com.maxtorgroup.democonsultas.domain.dto.MedicalConsultationDto;
import com.maxtorgroup.democonsultas.domain.dto.PatientDto;
import com.maxtorgroup.democonsultas.domain.dto.PatientRegisterDto;
import com.maxtorgroup.democonsultas.infrastructure.entity.Patient;
import com.maxtorgroup.democonsultas.infrastructure.mapper.EntityMapper;

import java.util.List;

public class ApplicationPatientService implements PatientService {

    private final EntityMapper mapper;
    private final PatientRepository patientRepository;
    private final MedicalConsultationRepository medicalConsultationRepository;

    public ApplicationPatientService(
            EntityMapper mapper,
            PatientRepository patientRepository,
            MedicalConsultationRepository medicalConsultationRepository) {
        this.mapper = mapper;
        this.patientRepository = patientRepository;
        this.medicalConsultationRepository = medicalConsultationRepository;
    }

    @Override
    public List<PatientDto> getPatients() {
        return mapper.patientsToDto(patientRepository.getPatients());
    }

    @Override
    public List<PatientDto> getPatientsByDoctorId(Long doctorId) {
        return mapper.patientsToDto(patientRepository.getPatientsByDoctorId(doctorId));
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

    @Override
    public List<MedicalConsultationDto> getMedicalConsultations(Long patientId) {
        return mapper.medicalConsultationsToDto(medicalConsultationRepository.getMedicalConsultationsByPatientId(patientId));
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
