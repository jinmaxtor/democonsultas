package com.maxtorgroup.democonsultas.domain.contract;

import com.maxtorgroup.democonsultas.infrastructure.entity.MedicalConsultation;

import java.util.List;

public interface MedicalConsultationRepository {
    List<MedicalConsultation> getMedicalConsultations();
    List<MedicalConsultation> getMedicalConsultationsByPatientId(Long patientId);
    List<MedicalConsultation> getMedicalConsultationsByDoctorId(Long doctorId);
    MedicalConsultation saveMedicalConsultation(MedicalConsultation medicalConsultation);
    Boolean deleteMedicalConsultationById(Long id);
}
