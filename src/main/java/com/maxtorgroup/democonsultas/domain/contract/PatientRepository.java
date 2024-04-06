package com.maxtorgroup.democonsultas.domain.contract;

import com.maxtorgroup.democonsultas.infrastructure.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientRepository {
    List<Patient> getPatients();
    Optional<Patient> getPatientById(Long id);
    Optional<Patient> getPatientByEmail(String email);
    Patient savePatient(Patient patient);
    Boolean deletePatientById(Long id);
}
