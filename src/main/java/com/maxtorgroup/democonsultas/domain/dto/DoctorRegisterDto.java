package com.maxtorgroup.democonsultas.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DoctorRegisterDto extends UserDto {
    private String specialty;
}
