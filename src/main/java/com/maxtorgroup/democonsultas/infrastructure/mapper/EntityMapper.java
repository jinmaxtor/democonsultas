package com.maxtorgroup.democonsultas.infrastructure.mapper;

import com.maxtorgroup.democonsultas.domain.dto.*;
import com.maxtorgroup.democonsultas.infrastructure.entity.Doctor;
import com.maxtorgroup.democonsultas.infrastructure.entity.MedicalConsultation;
import com.maxtorgroup.democonsultas.infrastructure.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EntityMapper {
    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    DoctorDto toDto(Doctor doctor);
    PatientDto toDto(Patient patient);

    @Mapping(target = "doctorId", source = "doctor.id")
    @Mapping(target = "patientId", source = "patient.id")
    @Mapping(target = "doctorName", expression = "java(medicalConsultation.getDoctor().getFullName())")
    @Mapping(target = "patientName", expression = "java(medicalConsultation.getPatient().getFullName())")
    MedicalConsultationDto toDto(MedicalConsultation medicalConsultation);

    List<DoctorDto> doctorsToDto(List<Doctor> doctors);
    List<PatientDto> patientsToDto(List<Patient> patients);
    List<MedicalConsultationDto> medicalConsultationsToDto(List<MedicalConsultation> medicalConsultations);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "medicalConsultations", ignore = true)
    Doctor toEntity(DoctorRegisterDto doctorDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "medicalConsultations", ignore = true)
    Patient toEntity(PatientRegisterDto patientDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "patient", ignore = true)
    MedicalConsultation toEntity(MedicalConsultationRegisterDto medicalConsultationDto);


}
