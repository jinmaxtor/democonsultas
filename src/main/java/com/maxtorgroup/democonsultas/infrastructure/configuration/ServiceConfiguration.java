package com.maxtorgroup.democonsultas.infrastructure.configuration;

import com.maxtorgroup.democonsultas.application.service.ApplicationDoctorService;
import com.maxtorgroup.democonsultas.application.service.ApplicationFileService;
import com.maxtorgroup.democonsultas.application.service.ApplicationPatientService;
import com.maxtorgroup.democonsultas.domain.contract.*;
import com.maxtorgroup.democonsultas.infrastructure.mapper.EntityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
    @Bean
    DoctorService doctorService(
            final EntityMapper mapper,
            final DoctorRepository doctorRepository,
            final MedicalConsultationRepository medicalConsultationRepository) {
        return new ApplicationDoctorService(mapper, doctorRepository, medicalConsultationRepository);
    }

    @Bean
    PatientService patientService(
            final EntityMapper mapper,
            final PatientRepository patientRepository,
            final MedicalConsultationRepository medicalConsultationRepository) {
        return new ApplicationPatientService(mapper, patientRepository, medicalConsultationRepository);
    }

    @Bean
    FileService fileService(final StorageProperties properties) {
        return new ApplicationFileService(properties);
    }
}
