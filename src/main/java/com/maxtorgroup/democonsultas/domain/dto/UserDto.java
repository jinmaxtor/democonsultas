package com.maxtorgroup.democonsultas.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class UserDto {
    private String firstName;
    private String lastName;
    private LocalDateTime birthDate;
    private String email;
    private String address;
    private String image;
}
