package com.maxtorgroup.democonsultas.infrastructure.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("patient")
public class Patient extends User {

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
    private List<MedicalConsultation> medicalConsultations = new ArrayList<>();
}
