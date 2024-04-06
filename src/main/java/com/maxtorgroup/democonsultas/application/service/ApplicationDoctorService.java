package com.maxtorgroup.democonsultas.application.service;

import com.maxtorgroup.democonsultas.domain.contract.DoctorRepository;
import com.maxtorgroup.democonsultas.domain.contract.DoctorService;
import com.maxtorgroup.democonsultas.domain.contract.MedicalConsultationRepository;
import com.maxtorgroup.democonsultas.domain.dto.DoctorDto;
import com.maxtorgroup.democonsultas.domain.dto.DoctorRegisterDto;
import com.maxtorgroup.democonsultas.domain.dto.MedicalConsultationDto;
import com.maxtorgroup.democonsultas.domain.dto.MedicalConsultationRegisterDto;
import com.maxtorgroup.democonsultas.infrastructure.entity.Doctor;
import com.maxtorgroup.democonsultas.infrastructure.entity.MedicalConsultation;
import com.maxtorgroup.democonsultas.infrastructure.mapper.EntityMapper;

import java.util.List;

public class ApplicationDoctorService implements DoctorService {

    private final EntityMapper mapper;
    private final DoctorRepository doctorRepository;
    private final MedicalConsultationRepository medicalConsultationRepository;

    public ApplicationDoctorService(
            EntityMapper mapper,
            DoctorRepository doctorRepository,
            MedicalConsultationRepository medicalConsultationRepository) {
        this.mapper = mapper;
        this.doctorRepository = doctorRepository;
        this.medicalConsultationRepository = medicalConsultationRepository;
    }

    @Override
    public List<DoctorDto> getDoctors() {
        return mapper.doctorsToDto(doctorRepository.getDoctors());
    }

    @Override
    public DoctorDto getDoctorById(Long id) {
        return mapper.toDto(doctorRepository.getDoctorById(id).orElse(null));
    }

    @Override
    public DoctorDto createDoctor(DoctorRegisterDto doctor) {
        validateDoctor(doctor);

        Doctor data = mapper.toEntity(doctor);
        return mapper.toDto(doctorRepository.saveDoctor(data));
    }

    @Override
    public DoctorDto updateDoctor(Long id, DoctorRegisterDto doctor) {
        Doctor data = mapper.toEntity(doctor);
        data.setId(id);
        return mapper.toDto(doctorRepository.saveDoctor(data));
    }

    @Override
    public Boolean deleteDoctor(Long id) {
        return doctorRepository.deleteDoctorById(id);
    }

    @Override
    public List<MedicalConsultationDto> getMedicalConsultations(Long doctorId) {
        return mapper.medicalConsultationsToDto(medicalConsultationRepository.getMedicalConsultationsByDoctorId(doctorId));
    }

    @Override
    public MedicalConsultationDto createMedicalConsultation(MedicalConsultationRegisterDto medicalConsultation) {
        validateMedicalConsultation(medicalConsultation);

        MedicalConsultation data = mapper.toEntity(medicalConsultation);
        return mapper.toDto(medicalConsultationRepository.saveMedicalConsultation(data));
    }

    @Override
    public MedicalConsultationDto updateMedicalConsultation(Long id, MedicalConsultationRegisterDto medicalConsultation) {
        validateMedicalConsultation(medicalConsultation);

        MedicalConsultation data = mapper.toEntity(medicalConsultation);
        data.setId(id);
        return mapper.toDto(medicalConsultationRepository.saveMedicalConsultation(data));
    }

    @Override
    public Boolean deleteMedicalConsultation(Long id) {
        medicalConsultationRepository.deleteMedicalConsultationById(id);
        return true;
    }

    private void validateDoctor(DoctorRegisterDto doctor) {
        Doctor existingDoctor = doctorRepository
                .getDoctorByEmail(doctor.getEmail())
                .orElse(null);

        if (existingDoctor != null) {
            throw new IllegalArgumentException("Doctor with email " + doctor.getEmail() + " already exists");
        }
    }

    private void validateMedicalConsultation(MedicalConsultationRegisterDto medicalConsultation) {
        if (medicalConsultation.getDoctorId() == null) {
            throw new IllegalArgumentException("La consulta medica debe tener un doctor asignado");
        }

        if (medicalConsultation.getPatientId() == null) {
            throw new IllegalArgumentException("La consulta medica debe tener un paciente asignado");
        }
    }
}
