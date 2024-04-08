package com.maxtorgroup.democonsultas.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MedicalConsultationRegisterDto {


    private String diagnostic;
    private String treatment;
    private Date date;

    private Long doctorId;
    private Long patientId;
}
