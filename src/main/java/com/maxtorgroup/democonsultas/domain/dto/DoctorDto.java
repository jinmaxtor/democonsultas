package com.maxtorgroup.democonsultas.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class DoctorDto extends DoctorRegisterDto {
    private Long id;
    private List<MedicalConsultationDto> medicalConsultations;
}
