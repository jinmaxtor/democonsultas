package com.maxtorgroup.democonsultas.domain.dto;

import lombok.Data;

@Data
public class PageRequestDto {
    private String name = "";
    private Integer page = 1;
    private Integer size = Integer.MAX_VALUE;
    private String sort = "id";
    private PageRequestSortDirection direction = PageRequestSortDirection.ASC;
}
