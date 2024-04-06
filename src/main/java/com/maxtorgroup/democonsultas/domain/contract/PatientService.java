package com.maxtorgroup.democonsultas.domain.contract;

import com.maxtorgroup.democonsultas.domain.dto.PatientDto;
import com.maxtorgroup.democonsultas.domain.dto.PatientRegisterDto;
import com.maxtorgroup.democonsultas.infrastructure.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    List<PatientDto> getPatients();
    PatientDto getPatientById(Long id);
    PatientDto createPatient(PatientRegisterDto patient);
    PatientDto updatePatient(Long id, PatientRegisterDto patient);
    Boolean deletePatient(Long id);
}
