package com.maxtorgroup.democonsultas.infrastructure.configuration;

import com.maxtorgroup.democonsultas.infrastructure.entity.Doctor;
import com.maxtorgroup.democonsultas.infrastructure.entity.MedicalConsultation;
import com.maxtorgroup.democonsultas.infrastructure.entity.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

@Configuration
public class PersistenceConfiguration {
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    SimpleJpaRepository<Doctor, Long> doctorDataRepository() {
        return new SimpleJpaRepository<>(Doctor.class, entityManager);
    }

    @Bean
    SimpleJpaRepository<Patient, Long> patientDataRepository() {
        return new SimpleJpaRepository<>(Patient.class, entityManager);
    }

    @Bean
    SimpleJpaRepository<MedicalConsultation, Long> medicalConsultationDataRepository() {
        return new SimpleJpaRepository<>(MedicalConsultation.class, entityManager);
    }
}
