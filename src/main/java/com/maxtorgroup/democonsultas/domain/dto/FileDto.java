package com.maxtorgroup.democonsultas.domain.dto;

import lombok.Data;

import java.net.URI;

@Data
public class FileDto {
    private String name;
    private String path;
    private String url;
}
