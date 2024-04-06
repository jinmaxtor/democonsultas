package com.maxtorgroup.democonsultas.domain.contract;

import com.maxtorgroup.democonsultas.domain.dto.DoctorDto;
import com.maxtorgroup.democonsultas.domain.dto.DoctorRegisterDto;
import com.maxtorgroup.democonsultas.domain.dto.MedicalConsultationDto;
import com.maxtorgroup.democonsultas.domain.dto.MedicalConsultationRegisterDto;

import java.util.List;

public interface DoctorService {
    List<DoctorDto> getDoctors();
    DoctorDto getDoctorById(Long id);
    DoctorDto createDoctor(DoctorRegisterDto doctor);
    DoctorDto updateDoctor(Long id, DoctorRegisterDto doctor);
    Boolean deleteDoctor(Long id);

    List<MedicalConsultationDto> getMedicalConsultations(Long doctorId);
    MedicalConsultationDto createMedicalConsultation(MedicalConsultationRegisterDto medicalConsultation);
    MedicalConsultationDto updateMedicalConsultation(Long id, MedicalConsultationRegisterDto medicalConsultation);
    Boolean deleteMedicalConsultation(Long id);
}
