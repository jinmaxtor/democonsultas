package com.maxtorgroup.democonsultas.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MedicalConsultationDto extends MedicalConsultationRegisterDto {
    private Long id;
    private String doctorName;
    private String patientName;
}
