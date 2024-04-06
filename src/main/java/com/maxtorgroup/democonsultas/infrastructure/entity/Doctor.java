package com.maxtorgroup.democonsultas.infrastructure.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("doctor")
public class Doctor extends User {
    private String specialty;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    private List<MedicalConsultation> medicalConsultations = new ArrayList<>();
}
