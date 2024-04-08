package com.maxtorgroup.democonsultas.infrastructure.persistence;

import com.maxtorgroup.democonsultas.domain.contract.PatientRepository;
import com.maxtorgroup.democonsultas.infrastructure.entity.MedicalConsultation;
import com.maxtorgroup.democonsultas.infrastructure.entity.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaPatientRepository implements PatientRepository {

    private final EntityManager entityManager;
    private final SimpleJpaRepository<Patient, Long> dataRepository;

    public JpaPatientRepository(EntityManager entityManager, SimpleJpaRepository<Patient, Long> dataRepository) {
        this.entityManager = entityManager;
        this.dataRepository = dataRepository;
    }

    @Override
    public List<Patient> getPatients() {
        return dataRepository.findAll();
    }

    @Override
    public List<Patient> getPatientsByDoctorId(Long doctorId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Patient> patientCriteria = criteriaBuilder.createQuery(Patient.class);
        Root<Patient> patient = patientCriteria.from(Patient.class);
        Join<Patient, MedicalConsultation> joinMedicalConsultation = patient.join("medicalConsultations");
        Predicate doctorCondition = criteriaBuilder.equal(joinMedicalConsultation.get("doctor").get("id"), doctorId);
        patientCriteria.select(patient)
                .where(doctorCondition);

        TypedQuery<Patient> query = entityManager.createQuery(patientCriteria);

        return query.getResultList();
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
