package com.maxtorgroup.democonsultas.domain.contract;

import com.maxtorgroup.democonsultas.infrastructure.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository {
    List<Doctor> getDoctors();
    Page<Doctor> getPageableDoctorsByName(String name, Pageable pageable);
    Optional<Doctor> getDoctorById(Long id);
    Optional<Doctor> getDoctorByEmail(String email);
    Doctor saveDoctor(Doctor doctor);
    Boolean deleteDoctorById(Long id);
}
