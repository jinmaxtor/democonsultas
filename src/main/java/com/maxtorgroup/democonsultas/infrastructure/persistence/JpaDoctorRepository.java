package com.maxtorgroup.democonsultas.infrastructure.persistence;

import com.maxtorgroup.democonsultas.domain.contract.DoctorRepository;
import com.maxtorgroup.democonsultas.infrastructure.entity.Doctor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaDoctorRepository implements DoctorRepository {

    private final SimpleJpaRepository<Doctor, Long> dataRepository;

    public JpaDoctorRepository(SimpleJpaRepository<Doctor, Long> dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public List<Doctor> getDoctors() {
        return dataRepository.findAll();
    }

    @Override
    public Optional<Doctor> getDoctorById(Long id) {
        return dataRepository.findById(id);
    }

    @Override
    public Optional<Doctor> getDoctorByEmail(String email) {
        Doctor doctor = new Doctor();
        doctor.setEmail(email);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.EXACT);

        Example<Doctor> criteriaExample = Example.of(doctor, matcher);

        return dataRepository.findBy(criteriaExample, q -> q.sortBy(Sort.by("id").descending())).first();
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return dataRepository.save(doctor);
    }

    @Override
    public Boolean deleteDoctorById(Long id) {
        dataRepository.deleteById(id);
        return true;
    }
}
