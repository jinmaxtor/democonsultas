package com.maxtorgroup.democonsultas.infrastructure.persistence;

import com.maxtorgroup.democonsultas.domain.contract.PatientRepository;
import com.maxtorgroup.democonsultas.infrastructure.entity.Patient;
import com.maxtorgroup.democonsultas.infrastructure.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaPatientRepository implements PatientRepository {

    private final SimpleJpaRepository<Patient, Long> dataRepository;

    public JpaPatientRepository(SimpleJpaRepository<Patient, Long> dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public List<Patient> getPatients() {
        return dataRepository.findAll();
    }

    @Override
    public Optional<Patient> getPatientById(Long id) {
        return dataRepository.findById(id);
    }

    @Override
    public Optional<Patient> getPatientByEmail(String email) {
        Patient patient = new Patient();
        patient.setEmail(email);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.EXACT);

        Example<Patient> creteriaExample = Example.of(patient, matcher);

        return dataRepository.findBy(creteriaExample, q -> q.sortBy(Sort.by("id").descending())).first();
    }

    @Override
    public Patient savePatient(Patient patient) {
        return dataRepository.save(patient);
    }

    @Override
    public Boolean deletePatientById(Long id) {
        dataRepository.deleteById(id);
        return true;
    }
}
