package com.maxtorgroup.democonsultas.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public abstract class UserDto {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String email;
    private String address;
    private String image;
}
