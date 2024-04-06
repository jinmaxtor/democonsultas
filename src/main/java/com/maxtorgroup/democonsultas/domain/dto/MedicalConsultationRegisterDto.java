package com.maxtorgroup.democonsultas.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MedicalConsultationRegisterDto {


    private String diagnostic;
    private String treatment;
    private LocalDateTime date;

    private Long doctorId;
    private Long patientId;
}
