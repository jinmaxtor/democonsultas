package com.maxtorgroup.democonsultas.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PatientDto extends PatientRegisterDto {
    private Long id;
    private List<MedicalConsultationDto> medicalConsultations;
}
