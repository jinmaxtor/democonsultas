package com.maxtorgroup.democonsultas.domain.contract;

import com.maxtorgroup.democonsultas.infrastructure.entity.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository {
    List<Doctor> getDoctors();
    Optional<Doctor> getDoctorById(Long id);
    Optional<Doctor> getDoctorByEmail(String email);
    Doctor saveDoctor(Doctor doctor);
    Boolean deleteDoctorById(Long id);
}
