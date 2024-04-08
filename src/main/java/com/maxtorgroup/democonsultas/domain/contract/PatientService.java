package com.maxtorgroup.democonsultas.domain.contract;

import com.maxtorgroup.democonsultas.domain.dto.MedicalConsultationDto;
import com.maxtorgroup.democonsultas.domain.dto.PatientDto;
import com.maxtorgroup.democonsultas.domain.dto.PatientRegisterDto;

import java.util.List;

public interface PatientService {
    List<PatientDto> getPatients();
    List<PatientDto> getPatientsByDoctorId(Long doctorId);
    PatientDto getPatientById(Long id);
    PatientDto createPatient(PatientRegisterDto patient);
    PatientDto updatePatient(Long id, PatientRegisterDto patient);
    Boolean deletePatient(Long id);
    List<MedicalConsultationDto> getMedicalConsultations(Long patientId);
}
