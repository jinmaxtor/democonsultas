package com.maxtorgroup.democonsultas.domain.contract;

import com.maxtorgroup.democonsultas.domain.dto.*;

import java.util.List;

public interface DoctorService {
    List<DoctorDto> getDoctors();
    PageDto<DoctorDto> getPageableDoctorsByName(PageRequestDto pageRequest);
    DoctorDto getDoctorById(Long id);
    DoctorDto createDoctor(DoctorRegisterDto doctor);
    DoctorDto updateDoctor(Long id, DoctorRegisterDto doctor);
    Boolean deleteDoctor(Long id);
    List<MedicalConsultationDto> getMedicalConsultations(Long doctorId);
    MedicalConsultationDto createMedicalConsultation(MedicalConsultationRegisterDto medicalConsultation);
    MedicalConsultationDto updateMedicalConsultation(Long id, MedicalConsultationRegisterDto medicalConsultation);
    Boolean deleteMedicalConsultation(Long id);
}
